<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use App\Models\Gejala;

class GejalaSeeder extends Seeder
{
    public function run()
    {
        $gejalas = [
            ['kode' => 'G001', 'nama' => 'Keinginan bermain game setiap waktu', 'cf' => 0.4],
            ['kode' => 'G002', 'nama' => 'Jam tidur kurang', 'cf' => 0.2],
            ['kode' => 'G003', 'nama' => 'Mudah emosional', 'cf' => 0.2],
            ['kode' => 'G004', 'nama' => 'Sering mengantuk', 'cf' => 0.2],
            ['kode' => 'G005', 'nama' => 'Tampak lesu', 'cf' => 0.2],
            ['kode' => 'G006', 'nama' => 'Pola makan tidak teratur', 'cf' => 0.4],
            ['kode' => 'G007', 'nama' => 'Kurang konsentrasi saat melakukan pekerjaan', 'cf' => 0.4],
            ['kode' => 'G008', 'nama' => 'Merasa murung, stress, atau sering marah', 'cf' => 0.2],
            ['kode' => 'G009', 'nama' => 'Malas disuruh mengerjakan sesuatu selain game', 'cf' => 0.4],
            ['kode' => 'G010', 'nama' => 'Boros mengeluarkan uang untuk bermain game', 'cf' => 0.2],
            ['kode' => 'G011', 'nama' => 'Antusias saat ditanya mengenai game', 'cf' => 0.8],
            ['kode' => 'G012', 'nama' => 'Mengalami masalah di rumah, sekolah atau kantor', 'cf' => 0.8],
            ['kode' => 'G013', 'nama' => 'Introvert atau jarang hidup bersosial', 'cf' => 0.8],
        ];

        foreach ($gejalas as $gejala) {
            Gejala::create($gejala);
        }
    }
}
