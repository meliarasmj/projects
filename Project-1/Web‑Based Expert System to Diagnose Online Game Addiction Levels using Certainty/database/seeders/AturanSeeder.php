<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use App\Models\Aturan;
use App\Models\Gejala;
use App\Models\Diagnosa;

class AturanSeeder extends Seeder
{
    public function run()
    {
        // Ambil ID diagnosa berdasarkan kodenya
        $kecanduanRingan = Diagnosa::where('kode', 'K01')->first()->id;
        $kecanduanSedang = Diagnosa::where('kode', 'K02')->first()->id;
        $kecanduanBerat = Diagnosa::where('kode', 'K03')->first()->id;

        // Aturan untuk Kecanduan Ringan (K01)
        $aturansK01 = [
            ['gejala_id' => Gejala::where('kode', 'G001')->first()->id, 'diagnosa_id' => $kecanduanRingan],
            ['gejala_id' => Gejala::where('kode', 'G002')->first()->id, 'diagnosa_id' => $kecanduanRingan],
            ['gejala_id' => Gejala::where('kode', 'G003')->first()->id, 'diagnosa_id' => $kecanduanRingan],
            // tambahkan aturan lainnya untuk K01
        ];

        // Aturan untuk Kecanduan Sedang (K02)
        $aturansK02 = [
            ['gejala_id' => Gejala::where('kode', 'G001')->first()->id, 'diagnosa_id' => $kecanduanSedang],
            ['gejala_id' => Gejala::where('kode', 'G002')->first()->id, 'diagnosa_id' => $kecanduanSedang],
            ['gejala_id' => Gejala::where('kode', 'G006')->first()->id, 'diagnosa_id' => $kecanduanSedang],
            // tambahkan aturan lainnya untuk K02
        ];

        // Aturan untuk Kecanduan Berat (K03)
        $aturansK03 = [
            ['gejala_id' => Gejala::where('kode', 'G001')->first()->id, 'diagnosa_id' => $kecanduanBerat],
            ['gejala_id' => Gejala::where('kode', 'G009')->first()->id, 'diagnosa_id' => $kecanduanBerat],
            ['gejala_id' => Gejala::where('kode', 'G010')->first()->id, 'diagnosa_id' => $kecanduanBerat],
            // tambahkan aturan lainnya untuk K03
        ];

        // Gabungkan semua aturan menjadi satu array
        $aturans = array_merge($aturansK01, $aturansK02, $aturansK03);

        // Simpan aturan-aturan ke dalam tabel Aturan
        foreach ($aturans as $aturan) {
            Aturan::create($aturan);
        }
    }
}
