@extends('layouts.master')

@section('content')
<head>
    <!-- Meta tags, title, and other links -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>

<div class="grow mb-3">
    <h5 class="text-16">Detail Hasil Diagnosa</h5>
</div>
<div class="card">
    <div class="card-body">

        @if(session('success'))
            <div class="alert alert-success">
                {{ session('success') }}
            </div>
        @endif

        <!-- Tabel untuk Nama -->
        <div class="table-responsive mb-3">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th scope="col">Nama</th>
                        <th scope="col">{{ $nama }}</th>
                    </tr>
                </thead>
            </table>
        </div>

        <h3>1. Bobot User</h3>
        <!-- Tabel untuk Bobot User -->
        <div class="table-responsive mb-3">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th scope="col">Kode</th>
                        <th scope="col">Value</th>
                    </tr>
                </thead>
                <tbody>
                    @if(is_array($bobotUser))
                        @foreach($bobotUser as $bobot)
                            <tr>
                                <td>G{{$loop->iteration}}</td>
                                <td>{{ $bobot }}</td>
                            </tr>
                        @endforeach
                    @else
                        <tr>
                            <td>Bobot User</td>
                            <td>{{ $bobotUser }}</td>
                        </tr>
                    @endif
                </tbody>
            </table>
        </div>

        <h3>2. Bobot Pakar</h3>
        <!-- Tabel untuk Bobot Pakar -->
        <div class="table-responsive mb-3">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th scope="col">Kode</th>
                        <th scope="col">Value</th>
                    </tr>
                </thead>
                <tbody>
                    @if(is_array($bobotPakar))
                        @foreach($bobotPakar as $bobot)
                            <tr>
                                <td>G{{$loop->iteration}}</td>
                                <td>{{ $bobot }}</td>
                            </tr>
                        @endforeach
                    @else
                        <tr>
                            <td>Bobot Pakar</td>
                            <td>{{ $bobotPakar }}</td>
                        </tr>
                    @endif
                </tbody>
            </table>
        </div>

        <h3>3. Perhitungan CF</h3>
        <!-- Tabel untuk Hasil CF -->
        <div class="table-responsive mb-3">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th scope="col">Kode</th>
                        <th scope="col">Value</th>
                    </tr>
                </thead>
                <tbody>
                    @if(is_array($CF))
                        @foreach($CF as $cf)
                            <tr>
                                <td>G{{$loop->iteration}}</td>
                                <td>{{ $cf }}</td>
                            </tr>
                        @endforeach
                    @else
                        <tr>
                            <td>Hasil CF</td>
                            <td>{{ $CF }}</td>
                        </tr>
                    @endif
                </tbody>
            </table>
        </div>

        <h3>Perhitungan CF Diagnosa Kecanduan Rendah</h3>
        <!-- Tabel untuk P1CF -->
        <div class="table-responsive mb-3">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th scope="col">kode</th>
                        <th scope="col">Value</th>
                    </tr>
                </thead>
                <tbody>
                    @foreach($cleanP1CF as $p1cf)
                        <tr>
                            <td>P1CF</td>
                            <td>{{ $p1cf }}</td>
                        </tr>
                    @endforeach
                </tbody>
            </table>
        </div>

        <!-- Tabel untuk P2CF -->
        <h3>Perhitungan CF Diagnosa Kecanduan Sedang</h3>
        <div class="table-responsive mb-3">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th scope="col">kode</th>
                        <th scope="col">Value</th>
                    </tr>
                </thead>
                <tbody>
                    @foreach($cleanP2CF as $p2cf)
                        <tr>
                            <td>P2CF</td>
                            <td>{{ $p2cf }}</td>
                        </tr>
                    @endforeach
                </tbody>
            </table>
        </div>

        <!-- Tabel untuk P3CF -->
        <h3>Perhitungan CF Diagnosa Kecanduan Berat</h3>
        <div class="table-responsive mb-3">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th scope="col">Field</th>
                        <th scope="col">Value</th>
                    </tr>
                </thead>
                <tbody>
                    @foreach($cleanP3CF as $p3cf)
                        <tr>
                            <td>P3CF</td>
                            <td>{{ $p3cf }}</td>
                        </tr>
                    @endforeach
                </tbody>
            </table>
        </div>

        <!-- Tabel untuk Hasil P1 -->
        <h3>Hasil Diagnosa Kecanduan Rendah</h3>
        <div class="table-responsive mb-3">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th scope="col">Hasil</th>
                        <th scope="col">{{ $resultP1 }}</th>
                    </tr>
                </thead>
            </table>
        </div>

        <!-- Tabel untuk Hasil P2 -->
        <h3>Hasil Diagnosa Kecanduan Sedang</h3>
        <div class="table-responsive mb-3">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th scope="col">Hasil</th>
                        <th scope="col">{{ $resultP2 }}</th>
                    </tr>
                </thead>
            </table>
        </div>

        <h3>Hasil Diagnosa Kecanduan Berat</h3>
        <!-- Tabel untuk Hasil P3 -->
        <div class="table-responsive mb-3">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th scope="col">Hasil</th>
                        <th scope="col">{{ $resultP3 }}</th>
                    </tr>
                </thead>
            </table>
        </div>

        <h3>Kesimpulan</h3>
        @if ($resultP1 > $resultP2 && $resultP1 > $resultP3 )
            <p>Jadi berdasarkan hasil perhitungan, di dapatkan hasil {{$nama}} mengalami kecanduan <b>tingkat rendah</b></p>
        @elseif ($resultP2 > $resultP1 && $resultP2 > $resultP3 )
            <p>Jadi berdasarkan hasil perhitungan, di dapatkan hasil {{$nama}} mengalami kecanduan <b>tingkat sedang</b></p>
        @elseif ($resultP3 > $resultP1 && $resultP3 > $resultP2 )
            <p>Jadi berdasarkan hasil perhitungan, di dapatkan hasil {{$nama}} mengalami kecanduan <b>tingkat tinggi</b></p>
        @endif

        <!-- Tombol kembali -->
        <a href="{{ route('hasil_diagnosa.index') }}" class="btn btn-primary mt-3">
            Kembali
        </a>
    </div>
</div>
<body>
    <!-- Your content here -->

    <!-- Scripts -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>

@endsection
