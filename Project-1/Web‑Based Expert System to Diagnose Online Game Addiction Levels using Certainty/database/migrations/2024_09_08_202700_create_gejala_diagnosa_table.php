<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateGejalaDiagnosaTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('gejala_diagnosa', function (Blueprint $table) {
            $table->id(); // Kolom id sebagai primary key
            $table->string('kode_gejala'); // Kolom kode_gejala tipe string
            $table->string('kode_diagnosa'); // Kolom kode_diagnosa tipe string
            $table->timestamps(); // Kolom created_at dan updated_at
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('gejala_diagnosa');
    }
}
