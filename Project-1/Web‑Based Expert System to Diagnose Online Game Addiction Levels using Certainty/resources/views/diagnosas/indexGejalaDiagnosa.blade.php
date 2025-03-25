@extends('layouts.master')

@section('content')
<div class="grow mb-3">
    <h5 class="text-16">Daftar Gejala Diagnosa</h5>
</div>
<div class="card">
    <div class="card-body">
                            @if (session()->get('role') == "user")

        <a href="/gejala_diagnosas/create/{{$kode}}">
            <button type="button" class="text-white btn bg-custom-500 border-custom-500 hover:text-white hover:bg-custom-600 hover:border-custom-600 focus:text-white focus:bg-custom-600 focus:border-custom-600 focus:ring focus:ring-custom-100 active:text-white active:bg-custom-600 active:border-custom-600 active:ring active:ring-custom-100 dark:ring-custom-400/20 mb-3">Tambah Gejala</button>
        </a>
        @endif
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
                    <th>Kode Gejala</th>
                                        @if (session()->get('role') == "user")

                    <th>Actions</th>
                    @endif
                </tr>
            </thead>
            <tbody>
                @foreach($diagnosas as $diagnosa)
                <tr>
                    <td>{{ $loop->iteration }}</td>
                    <td>{{ $diagnosa->kode_gejala }}</td>
                                        @if (session()->get('role') == "user")

                    <td>
                        
                        <form action="/gejala_diagnosa/{{$diagnosa->id}}" method="POST" style="display:inline;">
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
