@extends('layouts.master')

@section('content')
<div class="grow mb-3">
    <h5 class="text-16">Daftar Hasil Diagnosa</h5>
</div>
<div class="card">
    <div class="card-body">

        @if(session('success'))
            <div class="alert alert-success">
                {{ session('success') }}
            </div>
        @endif

        @if(isset($hasilDiagnosas) && count($hasilDiagnosas) > 0)
        <div class="table-responsive">
            <table id="hoverableTable" class="hover group" style="width:100%">
                <thead>
                    <tr>
                        <th scope="col">No</th>
                        <th scope="col">Nama</th>
                        <th scope="col">Hasil P1</th>
                        <th scope="col">Hasil P2</th>
                        <th scope="col">Hasil P2</th>
                        <th scope="col">Aksi</th>
                    </tr>
                </thead>
                <tbody>
                    @foreach($hasilDiagnosas as $hasilDiagnosa)
                    <tr>
                        <td>{{ $loop->iteration }}</td>
                        <td>{{ $hasilDiagnosa->nama }}</td>
                        <td>{{ $hasilDiagnosa->hasil_p1}}</td>
                        <td>{{ $hasilDiagnosa->hasil_p2}}</td>
                        <td>{{ $hasilDiagnosa->hasil_p3}}</td>
                        <td>
                            <a href="{{ route('hasil_diagnosa.show', $hasilDiagnosa->id) }}">
                                <button type="button" class="text-white btn bg-orange-500 border-orange-500 hover:text-white hover:bg-orange-600 hover:border-orange-600 focus:text-white focus:bg-orange-600 focus:border-orange-600 focus:ring focus:ring-orange-100 active:text-white active:bg-orange-600 active:border-orange-600 active:ring active:ring-orange-100 dark:ring-orange-400/20">Detail</button>
                            </a>
                                                @if (session()->get('role') == "user")

                            <form action="{{ route('hasil_diagnosa.destroy', $hasilDiagnosa->id) }}" method="POST" style="display:inline;">
                                @csrf
                                @method('DELETE')
                                <button type="submit" class="text-white bg-red-500 border-red-500 btn hover:text-white hover:bg-red-600 hover:border-red-600 focus:text-white focus:bg-red-600 focus:border-red-600 focus:ring focus:ring-red-100 active:text-white active:bg-red-600 active:border-red-600 active:ring active:ring-red-100 dark:ring-red-400/20" onclick="return confirm('Are you sure you want to delete this item?');">Delete</button>
                            </form>
                            @endif
                        </td>                  
                    </tr>
                    @endforeach
                </tbody>
            </table>
        </div>
        @else
            <p>Tidak ada data.</p>
        @endif
    </div>
</div>
@endsection
