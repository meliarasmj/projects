<?php

namespace App\Http\Controllers;

use App\Models\Jawaban;
use Illuminate\Http\Request;

class JawabanController extends Controller
{
    public function index()
    {
        $jawabans = Jawaban::all();
        return view('jawabans.index', compact('jawabans'));
    }

    public function create()
    {
        return view('jawabans.create');
    }

    public function store(Request $request)
    {
        $request->validate([
            'jawaban' => 'required|string|max:255',
            'bobot' => 'required|numeric',
        ]);

        Jawaban::create($request->all());

        return redirect()->route('jawabans.index')->with('success', 'Jawaban berhasil ditambahkan.');
    }

    public function edit($id)
    {
        $jawaban = Jawaban::findOrFail($id);
        return view('jawabans.edit', compact('jawaban'));
    }

    public function update(Request $request, $id)
    {
        $request->validate([
            'jawaban' => 'required|string|max:255',
            'bobot' => 'required|numeric',
        ]);

        $jawaban = Jawaban::findOrFail($id);
        $jawaban->update($request->all());

        return redirect()->route('jawabans.index')->with('success', 'Jawaban berhasil diupdate.');
    }

    public function destroy($id)
    {
        $jawaban = Jawaban::findOrFail($id);
        $jawaban->delete();

        return redirect()->route('jawabans.index')->with('success', 'Jawaban berhasil dihapus.');
    }
}
