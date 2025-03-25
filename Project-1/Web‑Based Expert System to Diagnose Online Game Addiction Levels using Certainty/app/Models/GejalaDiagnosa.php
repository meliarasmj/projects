<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class GejalaDiagnosa extends Model
{
    use HasFactory;

    protected $table = 'gejala_diagnosa'; // Sesuaikan dengan nama tabel yang digunakan


    protected $fillable = ['kode_gejala', 'kode_diagnosa'];
}
