<?php

namespace App\Http\Controllers;

use App\Models\Gejala;
use App\Models\Diagnosa;
use Illuminate\Http\Request;
use App\Models\GejalaDiagnosa;

class DiagnosaCrudController extends Controller
{
    public function index()
    {
        $diagnosas = Diagnosa::all();
        return view('diagnosas.index', compact('diagnosas'));
    }

    public function create()
    {
        return view('diagnosas.create');
    }

    public function store(Request $request)
    {
        $request->validate([
            'kode' => 'required|string|max:255|unique:diagnosas',
            'nama' => 'required|string|max:255',
        ]);

        Diagnosa::create($request->all());

        return redirect('/diagnosas')->with('success', 'Diagnosa berhasil ditambahkan.');
    }

    public function edit($id)
    {
        $diagnosa = Diagnosa::findOrFail($id);
        return view('diagnosas.edit', compact('diagnosa'));
    }

    public function update(Request $request, $id)
    {
        $request->validate([
            'kode' => 'required|string|max:255|unique:diagnosas,kode,' . $id,
            'nama' => 'required|string|max:255',
        ]);

        $diagnosa = Diagnosa::findOrFail($id);
        $diagnosa->update($request->all());

        return redirect('/diagnosas')->with('success', 'Diagnosa berhasil diupdate.');
    }

    public function destroy($id)
    {
        $diagnosa = Diagnosa::findOrFail($id);
        $diagnosa->delete();

        return redirect('/diagnosas')->with('success', 'Diagnosa berhasil dihapus.');
    }
}
