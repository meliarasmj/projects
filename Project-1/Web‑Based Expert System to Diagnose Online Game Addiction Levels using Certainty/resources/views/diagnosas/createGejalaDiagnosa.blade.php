@extends('layouts.master')

@section('content')
<div class="card">
    <div class="card-body">
        <h3 class="mb-3">Tambah Gejala Diagnosa</h3>
        @if(session('error'))
        <div class="alert alert-danger">
            {{ session('error') }}
        </div>
        @endif
        <form action="{{ route('diagnosas.store') }}" method="POST">
            @csrf
            <div class="mb-3">
                <label for="kode" class="inline-block mb-2 text-base font-medium">
                    Kode <span class="text-red-500">*</span>
                </label>
                <input 
                    type="text" 
                    name="kode_diagnosa" 
                    id="kode" 
                    value="{{ $kode }}" 
                    class="form-input border-slate-200 dark:border-zink-500 focus:outline-none focus:border-custom-500 disabled:bg-slate-100 dark:disabled:bg-zink-600 disabled:border-slate-300 dark:disabled:border-zink-500 dark:disabled:text-zink-200 disabled:text-slate-500 dark:text-zink-100 dark:bg-zink-700 dark:focus:border-custom-800 placeholder:text-slate-400 dark:placeholder:text-zink-200" 
                    readonly 
                    required>
            </div>
            <div class="mb-3">
                <label for="kode" class="inline-block mb-2 text-base font-medium">
                    Kode Gejala <span class="text-red-500">*</span>
                </label>
                <select name="kode_gejala" id="kode" class="form-select border-slate-200 dark:border-zink-500 focus:outline-none focus:border-custom-500 disabled:bg-slate-100 dark:disabled:bg-zink-600 disabled:border-slate-300 dark:disabled:border-zink-500 dark:disabled:text-zink-200 disabled:text-slate-500 dark:text-zink-100 dark:bg-zink-700 dark:focus:border-custom-800 placeholder:text-slate-400 dark:placeholder:text-zink-200" required>
                    <option value="" disabled selected>Pilih Nama Gejala</option>
                    @foreach ($gejalas as $g)
                        <option value="{{ $g->kode }}">{{ $g->nama }}</option>
                    @endforeach
                </select>
            </div>
            <button type="submit" class="text-white btn bg-custom-500 border-custom-500 hover:text-white hover:bg-custom-600 hover:border-custom-600 focus:text-white focus:bg-custom-600 focus:border-custom-600 focus:ring focus:ring-custom-100 active:text-white active:bg-custom-600 active:border-custom-600 active:ring active:ring-custom-100 dark:ring-custom-400/20">Tambah Data</button>
        </form>
    </div>
</div><!--end card-->
@endsection
