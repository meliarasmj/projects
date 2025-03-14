<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateAturansTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('aturans', function (Blueprint $table) {
            $table->id();
            $table->foreignId('gejala_id')->constrained('gejalas'); // Kolom untuk menyimpan ID gejala
            $table->foreignId('diagnosa_id')->constrained('diagnosas'); // Kolom untuk menyimpan ID diagnosa
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('aturans');
    }
}
