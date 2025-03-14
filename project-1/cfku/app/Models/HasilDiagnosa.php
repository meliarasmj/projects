<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class HasilDiagnosa extends Model
{
    use HasFactory;

    protected $fillable = [
        'nama', 
        'bobot_user', 
        'bobot_pakar', 
        'hasil_cf', 
        'p1cf', 
        'p2cf', 
        'p3cf', 
        'hasil_p1', 
        'hasil_p2', 
        'hasil_p3',
        'kesimpulan'
    ];
    
    protected $table = 'hasil_diagnosas'; // Sesuaikan dengan nama tabel yang digunakan
}
