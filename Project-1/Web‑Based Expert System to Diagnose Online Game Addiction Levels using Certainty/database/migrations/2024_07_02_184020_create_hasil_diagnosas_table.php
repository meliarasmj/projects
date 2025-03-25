<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateHasilDiagnosasTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('hasil_diagnosas', function (Blueprint $table) {
            $table->id();
            $table->string('nama');
            $table->json('jawaban_user');
            $table->float('hasil_cf');
            $table->float('persentase');
            $table->string('jenis_kecanduan');
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
        Schema::dropIfExists('hasil_diagnosas');
    }
}
