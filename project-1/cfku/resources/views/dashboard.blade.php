@extends('Layouts.master')

@push('title')
    Dashboard
@endpush

@section('content')
<h4 class="m3">SELAMAT DATANG DI SISTEM PAKAR PENDETEKSI KECANDUAN GAME ONLINE</h4>

    <div class="grid grid-cols-12 gap-x-5">
        <!-- Existing cards -->
        <div class="order-1 md:col-span-6 lg:col-span-3 col-span-12 2xl:order-1 bg-green-100 dark:bg-green-500/20 card 2xl:col-span-2 group-data-[skin=bordered]:border-green-500/20 relative overflow-hidden">
            <div class="card-body">
                <i data-lucide="kanban" class="absolute top-0 stroke-1 size-32 text-green-200/50 dark:text-green-500/20 ltr:-right-10 rtl:-left-10"></i>
                <div class="flex items-center justify-center bg-green-500 rounded-md size-12 text-15 text-green-50">
                    <i data-lucide="brain"></i>
                </div>
                <h5 class="mt-5 mb-2"><span class="counter-value" data-target="{{$gejala}}">0</span></h5>
                <p class="text-slate-500 dark:text-slate-200">Gejala</p>
            </div>
        </div><!--end col-->
        <div class="order-2 md:col-span-6 lg:col-span-3 col-span-12 2xl:order-1 bg-orange-100 dark:bg-orange-500/20 card 2xl:col-span-2 group-data-[skin=bordered]:border-orange-500/20 relative overflow-hidden">
            <div class="card-body">
                <i data-lucide="list-filter" class="absolute top-0 stroke-1 size-32 text-orange-200/50 dark:text-orange-500/20 ltr:-right-10 rtl:-left-10"></i>
                <div class="flex items-center justify-center bg-orange-500 rounded-md size-12 text-15 text-orange-50">
                    <i data-lucide="brain"></i>
                </div>
                <h5 class="mt-5 mb-2"><span class="counter-value" data-target="{{$diagnosa}}">0</span></h5>
                <p class="text-slate-500 dark:text-slate-200">Diagnosa</p>
            </div>
        </div><!--end col-->
        <div class="order-3 md:col-span-6 lg:col-span-3 col-span-12 2xl:order-1 bg-sky-100 dark:bg-sky-500/20 card 2xl:col-span-2 group-data-[skin=bordered]:border-sky-500/20 relative overflow-hidden">
            <div class="card-body">
                <i data-lucide="list-filter" class="absolute top-0 stroke-1 size-32 text-sky-200/50 dark:text-sky-500/20 ltr:-right-10 rtl:-left-10"></i>
                <div class="flex items-center justify-center rounded-md size-12 bg-sky-500 text-15 text-sky-50">
                    <i data-lucide="badge-check"></i>
                </div>
                <h5 class="mt-5 mb-2"><span class="counter-value" data-target="{{$jawaban}}">0</span></h5>
                <p class="text-slate-500 dark:text-slate-200">Jawaban</p>
            </div>
        </div><!--end col-->
        <div class="order-4 md:col-span-6 lg:col-span-3 col-span-12 2xl:order-1 bg-purple-100 dark:bg-purple-500/20 card 2xl:col-span-2 group-data-[skin=bordered]:border-purple-500/20 relative overflow-hidden">
            <div class="card-body">
                <i data-lucide="kanban" class="absolute top-0 stroke-1 size-32 text-purple-200/50 dark:text-purple-500/20 ltr:-right-10 rtl:-left-10"></i>
                <div class="flex items-center justify-center bg-purple-500 rounded-md size-12 text-15 text-purple-50">
                    <i data-lucide="book"></i>
                </div>
                <h5 class="mt-5 mb-2"><span class="counter-value" data-target="{{$hasil}}">0</span></h5>
                <p class="text-slate-500 dark:text-slate-200">Hasil diagnosa</p>
            </div>
        </div><!--end col-->
    </div>

    <div class="grid grid-cols-12 gap-x-5">
        <!-- Existing cards for kecanduan -->
        <div class="order-1 md:col-span-6 lg:col-span-3 col-span-12 2xl:order-1 bg-green-100 dark:bg-green-500/20 card 2xl:col-span-2 group-data-[skin=bordered]:border-green-500/20 relative overflow-hidden">
            <div class="card-body">
                <i data-lucide="kanban" class="absolute top-0 stroke-1 size-32 text-green-200/50 dark:text-green-500/20 ltr:-right-10 rtl:-left-10"></i>
                <div class="flex items-center justify-center bg-green-500 rounded-md size-12 text-15 text-green-50">
                    <i data-lucide="users"></i>
                </div>
                <h5 class="mt-5 mb-2"><span class="counter-value" data-target="{{$totalCount}}">0</span></h5>
                <p class="text-slate-500 dark:text-slate-200">Total Hasil</p>
            </div>
        </div><!--end col-->
        <div class="order-2 md:col-span-6 lg:col-span-3 col-span-12 2xl:order-1 bg-orange-100 dark:bg-orange-500/20 card 2xl:col-span-2 group-data-[skin=bordered]:border-orange-500/20 relative overflow-hidden">
            <div class="card-body">
                <i data-lucide="list-filter" class="absolute top-0 stroke-1 size-32 text-orange-200/50 dark:text-orange-500/20 ltr:-right-10 rtl:-left-10"></i>
                <div class="flex items-center justify-center bg-orange-500 rounded-md size-12 text-15 text-orange-50">
                    <i data-lucide="users"></i>
                </div>
                <h5 class="mt-5 mb-2"><span class="counter-value" data-target="{{$kecanduanRendahCount}}">0</span></h5>
                <p class="text-slate-500 dark:text-slate-200">Kecanduan Ringan</p>
            </div>
        </div><!--end col-->
        <div class="order-3 md:col-span-6 lg:col-span-3 col-span-12 2xl:order-1 bg-sky-100 dark:bg-sky-500/20 card 2xl:col-span-2 group-data-[skin=bordered]:border-sky-500/20 relative overflow-hidden">
            <div class="card-body">
                <i data-lucide="list-filter" class="absolute top-0 stroke-1 size-32 text-sky-200/50 dark:text-sky-500/20 ltr:-right-10 rtl:-left-10"></i>
                <div class="flex items-center justify-center rounded-md size-12 bg-sky-500 text-15 text-sky-50">
                    <i data-lucide="users"></i>
                </div>
                <h5 class="mt-5 mb-2"><span class="counter-value" data-target="{{$kecanduanSedangCount}}">0</span></h5>
                <p class="text-slate-500 dark:text-slate-200">Kecanduan Sedang</p>
            </div>
        </div><!--end col-->
        <div class="order-4 md:col-span-6 lg:col-span-3 col-span-12 2xl:order-1 bg-purple-100 dark:bg-purple-500/20 card 2xl:col-span-2 group-data-[skin=bordered]:border-purple-500/20 relative overflow-hidden">
            <div class="card-body">
                <i data-lucide="kanban" class="absolute top-0 stroke-1 size-32 text-purple-200/50 dark:text-purple-500/20 ltr:-right-10 rtl:-left-10"></i>
                <div class="flex items-center justify-center bg-purple-500 rounded-md size-12 text-15 text-purple-50">
                    <i data-lucide="users"></i>
                </div>
                <h5 class="mt-5 mb-2"><span class="counter-value" data-target="{{$kecanduanBeratCount}}">0</span></h5>
                <p class="text-slate-500 dark:text-slate-200">Kecanduan Berat</p>
            </div>
        </div><!--end col-->
    </div>

    <!-- Pie Chart -->
    <div class="grid grid-cols-12 gap-x-5 mt-10">
        <div class="col-span-12 bg-white dark:bg-gray-800 card p-5">
            <h3 class="text-xl font-semibold mb-4">Distribusi Kecanduan</h3>
            <div class="relative w-full h-80">
                <canvas id="kecanduanChart"></canvas>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script>
        var ctx = document.getElementById('kecanduanChart').getContext('2d');
        var kecanduanChart = new Chart(ctx, {
            type: 'pie',
            data: {
                labels: ['Kecanduan Rendah', 'Kecanduan Sedang', 'Kecanduan Berat'],
                datasets: [{
                    label: 'Persentase Kecanduan',
                    data: [
                        {{ $kecanduanRendahPercentage }},
                        {{ $kecanduanSedangPercentage }},
                        {{ $kecanduanBeratPercentage }}
                    ],
                    backgroundColor: [
                        '#FF6384',
                        '#36A2EB',
                        '#FFCE56'
                    ],
                    borderColor: '#fff',
                    borderWidth: 1
                }]
            },
            options: {
                responsive: true,
                plugins: {
                    legend: {
                        position: 'top',
                    },
                    tooltip: {
                        callbacks: {
                            label: function(tooltipItem) {
                                return tooltipItem.label + ': ' + tooltipItem.raw.toFixed(2) + '%';
                            }
                        }
                    }
                }
            }
        });
    </script>
@endsection
