<!DOCTYPE html>
<html lang="en" class="light scroll-smooth group" data-layout="vertical" data-sidebar="light" data-sidebar-size="lg" data-mode="light" data-topbar="light" data-skin="default" data-navbar="sticky" data-content="fluid" dir="ltr">

<head>
    <meta charset="utf-8">
    <title>Register</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
    <meta content="Minimal Admin & Dashboard Template" name="description">
    <meta content="StarCode Kh" name="author">
    <!-- App favicon -->
    <link rel="shortcut icon" href="{{asset('starcode')}}/assets/images/favicon.ico">
    <!-- Layout config Js -->
    <script src="{{asset('starcode')}}/assets/js/layout.js"></script>
    <!-- Icons CSS -->
    <!-- StarCode CSS -->
    <link rel="stylesheet" href="{{asset('starcode')}}/assets/css/starcode2.css">
</head>

<body class="flex items-center justify-center min-h-screen py-16 lg:py-10 bg-slate-50 dark:bg-zink-800 dark:text-zink-100 font-public">
    <div class="relative">
        <div class="mb-0 w-screen lg:mx-auto lg:w-[500px] card shadow-lg border-none shadow-slate-100 relative">
            <div class="!px-10 !py-12 card-body">
                <a href="#!">
                    <h1 class="text-center">Certainty Factor</h1>
                </a>
                <div class="mt-8 text-center">
                    <h4 class="mb-1 text-custom-500 dark:text-custom-500">Selamat Datang!</h4>
                    <p class="text-slate-500 dark:text-zink-200">Sistem pakar pendeteksi kecanduan game online</p>
                </div>
                @if(session('error'))
                <div class="alert alert-danger">
                    {{ session('error') }}
                </div>
                @endif
                <form action="/registerUser" method="POST" class="mt-10" id="registerForm">
                    @csrf
                    <div class="mb-3">
                        <label for="name" class="inline-block mb-2 text-base font-medium">Nama Lengkap</label>
                        <input type="text" name="name" id="name" class="form-input border-slate-200 dark:border-zink-500 focus:outline-none focus:border-custom-500 placeholder:text-slate-400 dark:placeholder:text-zink-200" placeholder="Masukkan nama lengkap" required>
                    </div>
                    <div class="mb-3">
                        <label for="email" class="inline-block mb-2 text-base font-medium">Email</label>
                        <input type="email" name="email" id="email" class="form-input border-slate-200 dark:border-zink-500 focus:outline-none focus:border-custom-500 placeholder:text-slate-400 dark:placeholder:text-zink-200" placeholder="Masukkan email" required>
                    </div>
                    <div class="mb-3">
                        <label for="password" class="inline-block mb-2 text-base font-medium">Password</label>
                        <input type="password" name="password" id="password" class="form-input border-slate-200 dark:border-zink-500 focus:outline-none focus:border-custom-500 placeholder:text-slate-400 dark:placeholder:text-zink-200" placeholder="Masukkan password" required>
                    </div>
                    <div class="mb-3">
                        <label for="password_confirmation" class="inline-block mb-2 text-base font-medium">Konfirmasi Password</label>
                        <input type="password" name="password_confirmation" id="password_confirmation" class="form-input border-slate-200 dark:border-zink-500 focus:outline-none focus:border-custom-500 placeholder:text-slate-400 dark:placeholder:text-zink-200" placeholder="Ulangi password" required>
                    </div>
                    <div class="mt-10">
                        <button type="submit" class="w-full text-white btn bg-custom-500 border-custom-500 hover:text-white hover:bg-custom-600 focus:ring focus:ring-custom-100">Register</button>
                    </div>
                </form>
                <p class="mt-5 text-center text-sm text-slate-500 dark:text-zink-200">Sudah punya akun? <a href="/login" class="text-custom-500">Login disini</a></p>
            </div>
        </div>
    </div>

    <script src='{{asset('starcode')}}/assets/libs/choices.js/public/{{asset('starcode')}}/assets/scripts/choices.min.js'></script>
    <script src="{{asset('starcode')}}/assets/libs/%40popperjs/core/umd/popper.min.js"></script>
    <script src="{{asset('starcode')}}/assets/libs/tippy.js/tippy-bundle.umd.min.js"></script>
    <script src="{{asset('starcode')}}/assets/libs/simplebar/simplebar.min.js"></script>
    <script src="{{asset('starcode')}}/assets/libs/prismjs/prism.js"></script>
    <script src="{{asset('starcode')}}/assets/libs/lucide/umd/lucide.js"></script>
    <script src="{{asset('starcode')}}/assets/js/starcode.bundle.js"></script>
</body>
</html>
