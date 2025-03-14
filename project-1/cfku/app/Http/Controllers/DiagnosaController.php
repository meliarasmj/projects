<?php

namespace App\Http\Controllers;

use App\Models\Gejala;
use App\Models\Jawaban;
use App\Models\Diagnosa;
use Illuminate\Http\Request;
use App\Models\HasilDiagnosa;
use App\Models\GejalaDiagnosa;

class DiagnosaController extends Controller
{
    public function showForm()
    {
        $gejala = Gejala::all();
        $jawaban = Jawaban::all();

        return view('diagnosa.form', compact('gejala','jawaban'));
    }

    public function calculateCF(Request $request)
    {
        $bobotPakar = Gejala::all()->pluck('cf', 'kode')->toArray();
        $bobotUser = $request->input('gejala'); // Array of gejala with frequency values
        
        $nama = $request->input('nama');

        $CF = $this->hitungCF($bobotPakar,$bobotUser);


        // Mengambil kode_gejala dari semua hasil yang cocok
        $P1 = GejalaDiagnosa::where('kode_diagnosa', 'K01')->pluck('kode_gejala');
        $P2 = GejalaDiagnosa::where('kode_diagnosa', 'K02')->pluck('kode_gejala');
        $P3 = GejalaDiagnosa::where('kode_diagnosa', 'K03')->pluck('kode_gejala');
        $arrayP1 = $P1->toArray();
        $arrayP2 = $P2->toArray();
        $arrayP3 = $P3->toArray();

        // Filter CF untuk mendapatkan hanya nilai yang kuncinya ada di P1
        $P1CF = array_filter($CF, function($key) use ($arrayP1) {
            return in_array($key, $arrayP1);
        }, ARRAY_FILTER_USE_KEY);

        $P2CF = array_filter($CF, function($key) use ($arrayP2) {
            return in_array($key, $arrayP2);
        }, ARRAY_FILTER_USE_KEY);

        $P3CF = array_filter($CF, function($key) use ($arrayP3) {
            return in_array($key, $arrayP3);
        }, ARRAY_FILTER_USE_KEY);

        //perhitungan akhir P1
        //hilangkan kunci array yang berupa kode diagnosa
        $cleanP1CF = array_values($P1CF);
        // Inisialisasi variabel untuk hasil
        $resultP1 = $cleanP1CF[0]; // Mulai dengan 0.1
        // Iterasi mulai dari index 1
        for ($i = 1; $i < count($cleanP1CF); $i++) {
            $resultP1 = $resultP1 + $cleanP1CF[$i] * (1 - $resultP1);
        }

        //hilangkan kunci array yang berupa kode diagnosa
        $cleanP2CF = array_values($P2CF);
        // Inisialisasi variabel untuk hasil
        $resultP2 = $cleanP2CF[0]; // Mulai dengan 0.1
        // Iterasi mulai dari index 1
        for ($i = 1; $i < count($cleanP2CF); $i++) {
            $resultP2 = $resultP2 + $cleanP2CF[$i] * (1 - $resultP2);
        }

        //hilangkan kunci array yang berupa kode diagnosa
        $cleanP3CF = array_values($P3CF);
        // Inisialisasi variabel untuk hasil
        $resultP3 = $cleanP3CF[0]; // Mulai dengan 0.1
        // Iterasi mulai dari index 1
        for ($i = 1; $i < count($cleanP3CF); $i++) {
            $resultP3 = $resultP3 + $cleanP3CF[$i] * (1 - $resultP3);
        }

        HasilDiagnosa::create([
            'nama' => $nama,
            'bobot_user' => json_encode($bobotUser),
            'bobot_pakar' => json_encode($bobotPakar),
            'hasil_cf' => json_encode($CF),
            'p1cf' => json_encode($cleanP1CF), // Mengubah array menjadi JSON string
            'p2cf' => json_encode($cleanP2CF),
            'p3cf' => json_encode($cleanP3CF),
            'hasil_p1' => $resultP1,
            'hasil_p2' => $resultP2,
            'hasil_p3' => $resultP3,
        ]);
        
        

        // Redirect ke halaman hasil dengan CF, persentase, dan jenis kecasnduan
        return view('diagnosa.hasil', compact('nama','bobotPakar','bobotUser','CF','cleanP1CF','cleanP2CF','cleanP3CF','resultP1','resultP2','resultP3'));
    }

    private function hitungCF($bobotUser,$BobotPakar)
    {
        if (count($bobotUser) === count($BobotPakar)) {
            // Mengalikan array
            $multiplied = array_map(function ($a, $b) {
                return $a * $b;
            }, $bobotUser, $BobotPakar);
        
            // Mengubah indeks menjadi G1, G2, ... G21
            $result = [];
            foreach ($multiplied as $index => $value) {
                $newIndex = 'G' . ($index + 1); // Membuat indeks baru G1, G2, dst.
                $result[$newIndex] = $value;
            }
        
            return $result;
        } else {
            $err = "Array tidak memiliki panjang yang sama.";
            return $err;
        }
    }

}
