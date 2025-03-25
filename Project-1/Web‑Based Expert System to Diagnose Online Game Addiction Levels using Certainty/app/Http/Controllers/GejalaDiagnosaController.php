<?php

namespace App\Http\Controllers;

use App\Models\Gejala;
use App\Models\Diagnosa;
use Illuminate\Http\Request;
use App\Models\GejalaDiagnosa;

class GejalaDiagnosaController extends Controller
{

    public function indexGejalaDiagnosa($kode)
    {
        $diagnosas = GejalaDiagnosa::where('kode_diagnosa',$kode)->get();
        return view('diagnosas.indexGejalaDiagnosa', compact('diagnosas','kode'));
    }

    public function createGejalaDiagnosa($kode)
    {
        $gejalas = Gejala::all();

        return view('diagnosas.createGejalaDiagnosa',compact(['gejalas','kode']));
    }

    public function storeGejalaDiagnosa(Request $request)
    {
        $request->validate([
            'kode_gejala' => 'required|string|max:255',
            'kode_diagnosa' => 'required|string|max:255',
        ]);

        GejalaDiagnosa::create($request->all());

        return redirect('gejala_diagnosa/' . urlencode($request->kode_diagnosa))->with('success', 'Diagnosa berhasil ditambahkan.');

        }

    public function destroyGejalaDiagnosa($id)
    {
        $diagnosa = GejalaDiagnosa::findOrFail($id);
        $diagnosa->delete();

        return redirect('gejala_diagnosa/' . urlencode($diagnosa->kode_diagnosa))->with('success', 'Diagnosa berhasil ditambahkan.');
    }
}
