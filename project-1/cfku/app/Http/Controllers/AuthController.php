<?php

namespace App\Http\Controllers;

use App\Helpers\UploadFile;
use Illuminate\Support\Str;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Hash;


class AuthController extends Controller
{
    public function loginIndex()
    {
        return view('Auth.login');
    }

    public function registerIndex()
    {
        return view('Auth.register');
    }

    public function registers(Request $request)
    {
        $getUser = DB::table("users")->where("email", $request->email)->first();
        if ($getUser != null) {
            return redirect("/registerIndex")->with("failed", "email sudah terdaftar");
        }


        DB::table("users")->insert([
            "name" => $request->name,
            "email" => $request->email,
            "password" => Hash::make($request->password),
            "role" => "admin",
            'remember_token' => Str::random(60),
        ]);

        return redirect("/registerIndex")->with("success", "Berhasil Mendaftar");
    }

    
    public function logins(Request $request)
    {   

        $data = DB::table("users")->where("email", $request->email)->first();

        if ($data == null) {

            return redirect("/")->with("failed", "email tidak terdaftar");
        }
        

        if (Hash::check($request->password, $data->password)) {

            $request->session()->put('remember_token', $data->remember_token);
            $request->session()->put('name', $data->name);
            $request->session()->put('id', $data->id);


            return redirect("/dashboard")->with("success", $request->session()->get("remember_token"));
        }

        return redirect("/")->with("failed", "gagal login");
    }

    public function registerIndexUser()
    {
        return view('Auth.registerUser');
    }

    public function registersUser(Request $request)
    {
        $getUser = DB::table("users")->where("email", $request->email)->first();
        if ($getUser != null) {
            return redirect("/registerIndex")->with("failed", "email sudah terdaftar");
        }


        DB::table("users")->insert([
            "name" => $request->name,
            "email" => $request->email,
            "password" => Hash::make($request->password),
            "role" => "user",
            'remember_token' => Str::random(60),
        ]);

        return redirect("/registerIndexUser")->with("success", "Berhasil Mendaftar");
    }


    public function logout(Request $request)
    {
        if (session()->get("remember_token") == "") {
            return redirect("/")->with("failed", "gagal login");
        }
        
        $request->session()->flush();
        
        return redirect("/")->with("success", "berhasil logout");
    }
}