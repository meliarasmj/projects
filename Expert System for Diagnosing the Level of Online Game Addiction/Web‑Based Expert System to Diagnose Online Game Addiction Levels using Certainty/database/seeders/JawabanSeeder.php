<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use App\Models\Jawaban;

class JawabanSeeder extends Seeder
{
    public function run()
    {
        $jawabans = [
            ['jawaban' => 'Tidak Pernah', 'bobot' => 0],
            ['jawaban' => 'Jarang', 'bobot' => 0.4],
            ['jawaban' => 'Terkadang', 'bobot' => 0.6],
            ['jawaban' => 'Sering', 'bobot' => 0.8],
            ['jawaban' => 'Sangat Sering', 'bobot' => 1],
        ];

        foreach ($jawabans as $jawaban) {
            Jawaban::create($jawaban);
        }
    }
}
