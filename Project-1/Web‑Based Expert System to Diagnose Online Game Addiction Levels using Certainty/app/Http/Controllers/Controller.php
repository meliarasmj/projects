<?php

namespace App\Http\Controllers;

use App\Models\Gejala;
use App\Models\Jawaban;
use App\Models\Diagnosa;
use App\Models\HasilDiagnosa;
use Illuminate\Support\Facades\DB;
use Illuminate\Routing\Controller as BaseController;
use Illuminate\Foundation\Validation\ValidatesRequests;
use Illuminate\Foundation\Auth\Access\AuthorizesRequests;

class Controller extends BaseController
{
    use AuthorizesRequests, ValidatesRequests;

    public function dashboard()
    {
        $gejala = Gejala::count();
        $diagnosa = Diagnosa::count();
        $jawaban = Jawaban::count();
        $hasil = HasilDiagnosa::count();

        // Hitung jumlah total record
        $totalCount = DB::table('hasil_diagnosas')->count();

        // Hitung jumlah masing-masing tipe kecanduan
        $kecanduanRendahCount = DB::table('hasil_diagnosas')->where('kesimpulan', 'Kecanduan Rendah')->count();
        $kecanduanSedangCount = DB::table('hasil_diagnosas')->where('kesimpulan', 'Kecanduan Sedang')->count();
        $kecanduanBeratCount = DB::table('hasil_diagnosas')->where('kesimpulan', 'Kecanduan Berat')->count();

        // Hitung persentase masing-masing tipe kecanduan
        $kecanduanRendahPercentage = ($totalCount > 0) ? ($kecanduanRendahCount / $totalCount) * 100 : 0;
        $kecanduanSedangPercentage = ($totalCount > 0) ? ($kecanduanSedangCount / $totalCount) * 100 : 0;
        $kecanduanBeratPercentage = ($totalCount > 0) ? ($kecanduanBeratCount / $totalCount) * 100 : 0;


        return view('dashboard',compact([   'gejala',
                                            'diagnosa',
                                            'jawaban',
                                            'hasil',
                                            'totalCount',
                                            'kecanduanRendahCount',
                                            'kecanduanSedangCount',
                                            'kecanduanBeratCount',
                                            'kecanduanRendahPercentage',
                                            'kecanduanSedangPercentage',
                                            'kecanduanBeratPercentage'
                                        ]));
    }
}
