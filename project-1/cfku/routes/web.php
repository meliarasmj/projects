<?php

use App\Http\Controllers\Controller;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\AuthController;
use App\Http\Controllers\GejalaController;
use App\Http\Controllers\JawabanController;
use App\Http\Controllers\DiagnosaController;
use App\Http\Controllers\DiagnosaCrudController;
use App\Http\Controllers\GejalaDiagnosaController;
use App\Http\Controllers\HasilDiagnosaController;
use Illuminate\Support\Facades\Auth;



/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "web" middleware group. Make something great!
|
*/


//Auth
Route::get('/', [AuthController::class, 'loginIndex']);
Route::post('/logins', [AuthController::class, 'logins']);

Route::get('/loginIndexUser', [AuthController::class, 'loginIndexUser']);
Route::post('/loginUser', [AuthController::class, 'loginUser']);

Route::get('/registerIndex', [AuthController::class, 'registerIndex']);
Route::post('/registers', [AuthController::class, 'registers']);

Route::get('/registerIndexUser', [AuthController::class, 'registerIndexUser']);
Route::post('/registerUser', [AuthController::class, 'registersUser']);

Route::get('/logout', [AuthController::class, 'logout']);


Route::get('/dashboard', [Controller::class, 'dashboard'])->middleware('checkToken');

Route::resource('gejalas', GejalaController::class)->middleware('checkToken');

// Route untuk menampilkan daftar diagnosa
Route::get('diagnosas', [DiagnosaCrudController::class, 'index'])->middleware('checkToken');
Route::get('diagnosas/create', [DiagnosaCrudController::class, 'create'])->middleware('checkToken');
Route::post('diagnosas', [DiagnosaCrudController::class, 'store'])->middleware('checkToken');
Route::get('diagnosas/{id}/edit', [DiagnosaCrudController::class, 'edit'])->middleware('checkToken');
Route::put('diagnosas/{id}', [DiagnosaCrudController::class, 'update'])->middleware('checkToken');
Route::delete('diagnosas/{id}', [DiagnosaCrudController::class, 'destroy'])->middleware('checkToken');

Route::resource('jawabans', JawabanController::class)->middleware('checkToken');
Route::resource('hasil_diagnosa', HasilDiagnosaController::class)->middleware('checkToken');

Route::get('/diagnosa/form', [DiagnosaController::class, 'showForm'])->name('diagnosa.form')->middleware('checkToken');
Route::post('/diagnosa/calculate', [DiagnosaController::class, 'calculateCF'])->name('diagnosa.calculate')->middleware('checkToken');


Route::get('gejala_diagnosa/{kode}', [GejalaDiagnosaController::class, 'indexGejalaDiagnosa'])->middleware('checkToken')->name('diagnosas.index');
Route::get('gejala_diagnosas/create/{kode}', [GejalaDiagnosaController::class, 'createGejalaDiagnosa'])->middleware('checkToken')->name('diagnosas.create');
Route::post('gejala_diagnosa', [GejalaDiagnosaController::class, 'storeGejalaDiagnosa'])->middleware('checkToken')->name('diagnosas.store');
Route::delete('gejala_diagnosa/{id}', [GejalaDiagnosaController::class, 'destroyGejalaDiagnosa'])->middleware('checkToken')->name('diagnosas.destroy');

Auth::routes(['verify' => true]);

Auth::routes();

Route::get('/home', [App\Http\Controllers\HomeController::class, 'index'])->name('home');
