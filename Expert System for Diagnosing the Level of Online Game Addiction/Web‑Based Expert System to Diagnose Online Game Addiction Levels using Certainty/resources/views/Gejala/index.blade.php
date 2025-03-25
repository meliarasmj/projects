@extends('layouts.master')

@section('content')
<div class="grow mb-3">
    <h5 class="text-16">GEJALA</h5>
</div>  
<div class="card">
    <div class="card-body">
        @if (session()->get('role') == "user")
            <a href="{{ route('gejalas.create') }}">
                <button type="button" class="text-white btn bg-custom-500 border-custom-500 hover:text-white hover:bg-custom-600 hover:border-custom-600 focus:text-white focus:bg-custom-600 focus:border-custom-600 focus:ring focus:ring-custom-100 active:text-white active:bg-custom-600 active:border-custom-600 active:ring active:ring-custom-100 dark:ring-custom-400/20 mb-3">Tambah Gejala</button>
            </a>
        @endif
        @if(session('error'))
            <div class="alert alert-danger">
                {{ session('error') }}
            </div>
        @endif

        @if(isset($gejalas) && count($gejalas) > 0)
        <table id="hoverableTable" class="hover group" style="width:100%">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Kode</th>
                    <th>Nama</th>
                    <th>CF</th>
                    @if (session()->get('role') == "user")
                        <th>Actions</th>
                    @endif
                </tr>
            </thead>
            <tbody>
                @foreach($gejalas as $gejala)
                <tr>
                    <td>{{ $loop->iteration }}</td>
                    <td>{{ $gejala->kode }}</td>
                    <td>{{ $gejala->nama }}</td>
                    <td>{{ $gejala->cf }}</td>
                    @if (session()->get('role') == "user")

                    <td>
                        <a href="{{ route('gejalas.edit', $gejala->id) }}">
                            <button type="button" class="text-white btn bg-orange-500 border-orange-500 hover:text-white hover:bg-orange-600 hover:border-orange-600 focus:text-white focus:bg-orange-600 focus:border-orange-600 focus:ring focus:ring-orange-100 active:text-white active:bg-orange-600 active:border-orange-600 active:ring active:ring-orange-100 dark:ring-orange-400/20">Edit</button>
                        </a>
                        
                        <form action="{{ route('gejalas.destroy', $gejala->id) }}" method="POST" style="display:inline;">
                            @csrf
                            @method('DELETE')
                            <button type="submit" class="text-white bg-red-500 border-red-500 btn hover:text-white hover:bg-red-600 hover:border-red-600 focus:text-white focus:bg-red-600 focus:border-red-600 focus:ring focus:ring-red-100 active:text-white active:bg-red-600 active:border-red-600 active:ring active:ring-red-100 dark:ring-red-400/20" onclick="return confirm('Are you sure you want to delete this item?');">Delete</button>
                        </form>
                    </td>
                    @endif
                </tr>
                @endforeach
            </tbody>
        </table>
        @else
            <p>No data available.</p>
        @endif
    </div>
</div><!--end card-->
@endsection
