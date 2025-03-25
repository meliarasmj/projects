<?php

namespace App\Http\Controllers;

use App\Models\Gejala;
use Illuminate\Http\Request;

class GejalaController extends Controller
{
    public function index()
    {
        $gejalas = Gejala::all();
        return view('Gejala.index', compact('gejalas'));
    }

    public function create()
    {
        return view('Gejala.create');
    }

    public function store(Request $request)
    {
        $request->validate([
            'kode' => 'required',
            'nama' => 'required',
            'cf' => 'required|numeric',
        ]);

        Gejala::create($request->all());

        return redirect('/gejalas')->with('success', 'Gejala created successfully.');
    }

    public function show(Gejala $gejala)
    {
        return view('Gejala.show', compact('gejala'));
    }

    public function edit(Gejala $gejala)
    {
        return view('Gejala.edit', compact('gejala'));
    }

    public function update(Request $request, Gejala $gejala)
    {
        $request->validate([
            'kode' => 'required',
            'nama' => 'required',
            'cf' => 'required|numeric',
        ]);

        $gejala->update($request->all());

        return redirect('/gejalas')->with('success', 'Gejala updated successfully');
    }

    public function destroy(Gejala $gejala)
    {
        $gejala->delete();

        return redirect('/gejalas')->with('success', 'Gejala deleted successfully');
    }
}
