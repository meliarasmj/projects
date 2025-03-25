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
                        <th scope="col">{{ $hasilDiagnosa['nama'] }}</th>
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
                    @php
                        $bobotUser = json_decode($hasilDiagnosa['bobot_user'], true);
                    @endphp
                    @foreach($bobotUser as $kode => $value)
                        <tr>
                            <td>{{ $kode }}</td>
                            <td>{{ $value }}</td>
                        </tr>
                    @endforeach
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
                    @php
                        $bobotPakar = json_decode($hasilDiagnosa['bobot_pakar'], true);
                    @endphp
                    @foreach($bobotPakar as $kode => $value)
                        <tr>
                            <td>{{ $kode }}</td>
                            <td>{{ $value }}</td>
                        </tr>
                    @endforeach
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
                    @php
                        $hasilCf = json_decode($hasilDiagnosa['hasil_cf'], true);
                    @endphp
                    @foreach($hasilCf as $kode => $value)
                        <tr>
                            <td>{{ $kode }}</td>
                            <td>{{ $value }}</td>
                        </tr>
                    @endforeach
                </tbody>
            </table>
        </div>

        <h3>Perhitungan CF Diagnosa Kecanduan Rendah</h3>
        <!-- Tabel untuk P1CF -->
        <div class="table-responsive mb-3">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th scope="col">Kode</th>
                        <th scope="col">Value</th>
                    </tr>
                </thead>
                <tbody>
                    @php
                        $p1cf = json_decode($hasilDiagnosa['p1cf']);
                    @endphp
                    @foreach($p1cf as $index => $value)
                        <tr>
                            <td>P1CF{{ $index + 1 }}</td>
                            <td>{{ $value }}</td>
                        </tr>
                    @endforeach
                </tbody>
            </table>
        </div>

        <h3>Perhitungan CF Diagnosa Kecanduan Sedang</h3>
        <!-- Tabel untuk P2CF -->
        <div class="table-responsive mb-3">
            <table
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th scope="col">Kode</th>
                        <th scope="col">Value</th>
                    </tr>
                </thead>
                <tbody>
                    @php
                        $p2cf = json_decode($hasilDiagnosa['p2cf']);
                    @endphp
                    @foreach($p2cf as $index => $value)
                        <tr>
                            <td>P2CF{{ $index + 1 }}</td>
                            <td>{{ $value }}</td>
                        </tr>
                    @endforeach
                </tbody>
            </table>
        </div>

        <h3>Perhitungan CF Diagnosa Kecanduan Berat</h3>
        <!-- Tabel untuk P3CF -->
        <div class="table-responsive mb-3">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th scope="col">Kode</th>
                        <th scope="col">Value</th>
                    </tr>
                </thead>
                <tbody>
                    @php
                        $p3cf = json_decode($hasilDiagnosa['p3cf']);
                    @endphp
                    @foreach($p3cf as $index => $value)
                        <tr>
                            <td>P3CF{{ $index + 1 }}</td>
                            <td>{{ $value }}</td>
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
                        <th scope="col">{{ $hasilDiagnosa['hasil_p1'] }} ({{ $hasilDiagnosa['hasil_p1'] * 100 }}%) </th>
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
                        <th scope="col">{{ $hasilDiagnosa['hasil_p2'] }} ({{ $hasilDiagnosa['hasil_p2'] * 100 }}%)</th>
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
                        <th scope="col">{{ $hasilDiagnosa['hasil_p3'] }} ({{ $hasilDiagnosa['hasil_p3'] * 100 }}%)</th>
                    </tr>
                </thead>
            </table>
        </div>

        <h3>Kesimpulan</h3>
        @php
            $resultP1 = (float) $hasilDiagnosa['hasil_p1'];
            $resultP2 = (float) $hasilDiagnosa['hasil_p2'];
            $resultP3 = (float) $hasilDiagnosa['hasil_p3'];
        @endphp
        @if ($resultP1 > $resultP2 && $resultP1 > $resultP3)
            <p>Jadi berdasarkan hasil perhitungan, didapatkan hasil {{ $hasilDiagnosa['nama'] }} mengalami kecanduan <b>tingkat rendah dengan hasil sebesar ({{ $hasilDiagnosa['hasil_p1'] * 100 }}%)</b></p>
        @elseif ($resultP2 > $resultP1 && $resultP2 > $resultP3)
            <p>Jadi berdasarkan hasil perhitungan, didapatkan hasil {{ $hasilDiagnosa['nama'] }} mengalami kecanduan <b>tingkat sedang dengan hasil sebesar ({{ $hasilDiagnosa['hasil_p2'] * 100 }}%)</b></p>
        @elseif ($resultP3 > $resultP1 && $resultP3 > $resultP2)
            <p>Jadi berdasarkan hasil perhitungan, didapatkan hasil {{ $hasilDiagnosa['nama'] }} mengalami kecanduan <b>tingkat tinggi dengan hasil sebesar ({{ $hasilDiagnosa['hasil_p3'] * 100 }}%)</b></p>
        @endif

        <!-- Tombol kembali -->
        <a href="{{ route('hasil_diagnosa.index') }}" class="btn btn-primary mt-3">
            Kembali
        </a>
    </div>
</div>

<!-- Scripts -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

@endsection
