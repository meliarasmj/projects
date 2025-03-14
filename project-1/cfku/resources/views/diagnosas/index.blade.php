@extends('layouts.master')

@section('content')
<div class="grow mb-3">
    <h5 class="text-16">Daftar Diagnosa</h5>
</div>
<div class="card">
    <div class="card-body">
        {{-- <a href="/diagnosas/create">
            <button type="button" class="text-white btn bg-custom-500 border-custom-500 hover:text-white hover:bg-custom-600 hover:border-custom-600 focus:text-white focus:bg-custom-600 focus:border-custom-600 focus:ring focus:ring-custom-100 active:text-white active:bg-custom-600 active:border-custom-600 active:ring active:ring-custom-100 dark:ring-custom-400/20 mb-3">Tambah Diagnosa</button>
        </a> --}}
        @if(session('success'))
            <div class="alert alert-success">
                {{ session('success') }}
            </div>
        @endif

        @if(isset($diagnosas) && count($diagnosas) > 0)
        <table id="hoverableTable" class="hover group" style="width:100%">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Kode</th>
                    <th>Nama</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                @foreach($diagnosas as $diagnosa)
                <tr>
                    <td>{{ $loop->iteration }}</td>
                    <td>{{ $diagnosa->kode }}</td>
                    <td>{{ $diagnosa->nama }}</td>
                    <td>
                        <a href="gejala_diagnosa/{{$diagnosa->kode}}">
                            <button type="button" class="text-white btn bg-orange-500 border-orange-500 hover:text-white hover:bg-orange-600 hover:border-orange-600 focus:text-white focus:bg-orange-600 focus:border-orange-600 focus:ring focus:ring-orange-100 active:text-white active:bg-orange-600 active:border-orange-600 active:ring active:ring-orange-100 dark:ring-orange-400/20">Gejala</button>
                        </a>
                                            @if (session()->get('role') == "user")

                        <a href="/diagnosas/{{$diagnosa->id}}/edit">
                            <button type="button" class="text-white btn bg-orange-500 border-orange-500 hover:text-white hover:bg-orange-600 hover:border-orange-600 focus:text-white focus:bg-orange-600 focus:border-orange-600 focus:ring focus:ring-orange-100 active:text-white active:bg-orange-600 active:border-orange-600 active:ring active:ring-orange-100 dark:ring-orange-400/20">Edit</button>
                        </a>
                        @endif
                        
                        {{-- <form action="diagnosas/{{$diagnosa->id}}" method="POST" style="display:inline;">
                            @csrf
                            @method('DELETE')
                            <button type="submit" class="text-white bg-red-500 border-red-500 btn hover:text-white hover:bg-red-600 hover:border-red-600 focus:text-white focus:bg-red-600 focus:border-red-600 focus:ring focus:ring-red-100 active:text-white active:bg-red-600 active:border-red-600 active:ring active:ring-red-100 dark:ring-red-400/20" onclick="return confirm('Are you sure you want to delete this item?');">Delete</button>
                        </form> --}}
                    </td>
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
