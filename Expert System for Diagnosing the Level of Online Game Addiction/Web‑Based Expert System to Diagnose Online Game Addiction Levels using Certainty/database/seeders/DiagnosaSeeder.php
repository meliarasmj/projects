<?php
namespace Database\Seeders;

use Illuminate\Database\Seeder;
use App\Models\Diagnosa;

class DiagnosaSeeder extends Seeder
{
    public function run()
    {
        $diagnosas = [
            ['kode' => 'K01', 'nama' => 'Kecanduan Ringan', 'cf_min' => 0, 'cf_max' => 0.5],
            ['kode' => 'K02', 'nama' => 'Kecanduan Sedang', 'cf_min' => 0.5, 'cf_max' => 0.8],
            ['kode' => 'K03', 'nama' => 'Kecanduan Berat', 'cf_min' => 0.8, 'cf_max' => 1],
        ];

        foreach ($diagnosas as $diagnosa) {
            Diagnosa::create($diagnosa);
        }
    }
}
