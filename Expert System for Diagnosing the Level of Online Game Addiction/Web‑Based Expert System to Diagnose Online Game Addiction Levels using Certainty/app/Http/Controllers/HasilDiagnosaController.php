<?php

// app/Http/Controllers/HasilDiagnosaController.php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\HasilDiagnosa;

class HasilDiagnosaController extends Controller
{
    public function index()
    {
        $hasilDiagnosas = HasilDiagnosa::all();
        return view('hasil_diagnosa.index', compact('hasilDiagnosas'));
    }

    public function show($id)
    {
        $hasilDiagnosa = HasilDiagnosa::findOrFail($id);
        $hasilDiagnosa = $hasilDiagnosa->toArray();


        return view('hasil_diagnosa.show', compact('hasilDiagnosa'));
    }

    public function destroy($id)
    {
        $hasilDiagnosa = HasilDiagnosa::findOrFail($id);
        $hasilDiagnosa->delete();

        return redirect()->route('hasil_diagnosa.index')
                         ->with('success', 'Hasil Diagnosa berhasil dihapus.');
    }
}
