@extends('layouts.master')

@section('content')
<div class="card">
    <div class="card-body">
        <h3 class="mb-3">Edit Data Gejala</h3>
        @if(session('error'))
        <div class="alert alert-danger">
            {{ session('error') }}
        </div>
        @endif
        <form action="{{ route('gejalas.update', ['gejala' => $gejala->id]) }}" method="POST">
            @csrf
            @method('PUT')

            <div class="mb-3">
                <label for="kode" class="inline-block mb-2 text-base font-medium">Kode <span class="text-red-500">*</span></label>
                <input placeholder="Masukkan kode gejala" type="text" name="kode" id="kode" class="form-input border-slate-200 dark:border-zink-500 focus:outline-none focus:border-custom-500 disabled:bg-slate-100 dark:disabled:bg-zink-600 disabled:border-slate-300 dark:disabled:border-zink-500 dark:disabled:text-zink-200 disabled:text-slate-500 dark:text-zink-100 dark:bg-zink-700 dark:focus:border-custom-800 placeholder:text-slate-400 dark:placeholder:text-zink-200" value="{{ $gejala->kode }}" required>
            </div>
            <div class="mb-3">
                <label for="nama" class="inline-block mb-2 text-base font-medium">Nama <span class="text-red-500">*</span></label>
                <input placeholder="Masukkan nama gejala" type="text" name="nama" id="nama" class="form-input border-slate-200 dark:border-zink-500 focus:outline-none focus:border-custom-500 disabled:bg-slate-100 dark:disabled:bg-zink-600 disabled:border-slate-300 dark:disabled:border-zink-500 dark:disabled:text-zink-200 disabled:text-slate-500 dark:text-zink-100 dark:bg-zink-700 dark:focus:border-custom-800 placeholder:text-slate-400 dark:placeholder:text-zink-200" value="{{ $gejala->nama }}" required>
            </div>
            <div class="mb-3">
                <label for="cf" class="inline-block mb-2 text-base font-medium">CF <span class="text-red-500">*</span></label>
                <input placeholder="Masukkan CF gejala" type="number" step="0.1" name="cf" id="cf" class="form-input border-slate-200 dark:border-zink-500 focus:outline-none focus:border-custom-500 disabled:bg-slate-100 dark:disabled:bg-zink-600 disabled:border-slate-300 dark:disabled:border-zink-500 dark:disabled:text-zink-200 disabled:text-slate-500 dark:text-zink-100 dark:bg-zink-700 dark:focus:border-custom-800 placeholder:text-slate-400 dark:placeholder:text-zink-200" value="{{ $gejala->cf }}" required>
            </div>
            <button type="submit" class="text-white btn bg-custom-500 border-custom-500 hover:text-white hover:bg-custom-600 hover:border-custom-600 focus:text-white focus:bg-custom-600 focus:border-custom-600 focus:ring focus:ring-custom-100 active:text-white active:bg-custom-600 active:border-custom-600 active:ring active:ring-custom-100 dark:ring-custom-400/20">Simpan Perubahan</button>
        </form>
    </div>
</div><!--end card-->
@endsection
