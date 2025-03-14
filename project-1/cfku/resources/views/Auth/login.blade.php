    <!DOCTYPE html>
    <html lang="en" class="light scroll-smooth group" data-layout="vertical" data-sidebar="light" data-sidebar-size="lg" data-mode="light" data-topbar="light" data-skin="default" data-navbar="sticky" data-content="fluid" dir="ltr">

    <head>
        <meta charset="utf-8">
        <title>Sign In</title>
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
            <div class="absolute hidden opacity-50 ltr:-left-16 rtl:-right-16 -top-10 md:block">
                <svg version="1.2" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 125 316" width="125" height="316">
                    <title>&lt;Group&gt;</title>
                    <g id="&lt;Group&gt;">
                        <path id="&lt;Path&gt;" class="fill-custom-100/50 dark:fill-custom-950/50" d="m23.4 221.8l-1.3-3.1v-315.3l1.3 3.1z"></path>
                        <path id="&lt;Path&gt;" class="fill-custom-100 dark:fill-custom-950" d="m31.2 229.6l-1.3-3.1v-315.3l1.3 3.1z"></path>
                        <path id="&lt;Path&gt;" class="fill-custom-200/50 dark:fill-custom-900/50" d="m39 237.4l-1.3-3.1v-315.3l1.3 3.1z"></path>
                        <path id="&lt;Path&gt;" class="fill-custom-200/75 dark:fill-custom-900/75" d="m46.8 245.2l-1.3-3.1v-315.3l1.3 3.1z"></path>
                        <path id="&lt;Path&gt;" class="fill-custom-200 dark:fill-custom-900" d="m54.6 253.1l-1.3-3.1v-315.4l1.3 3.1z"></path>
                        <path id="&lt;Path&gt;" class="fill-custom-300/50 dark:fill-custom-800/50" d="m62.4 260.9l-1.2-3.1v-315.4l1.2 3.1z"></path>
                        <path id="&lt;Path&gt;" class="fill-custom-300/75 dark:fill-custom-800/75" d="m70.3 268.7l-1.3-3.1v-315.4l1.3 3.1z"></path>
                        <path id="&lt;Path&gt;" class="fill-custom-300 dark:fill-custom-800" d="m78.1 276.5l-1.3-3.1v-315.3l1.3 3.1z"></path>
                        <path id="&lt;Path&gt;" class="fill-custom-400/50 dark:fill-custom-700/50" d="m85.9 284.3l-1.3-3.1v-315.3l1.3 3.1z"></path>
                        <path id="&lt;Path&gt;" class="fill-custom-400/75 dark:fill-custom-700/75" d="m93.7 292.1l-1.3-3.1v-315.3l1.3 3.1z"></path>
                        <path id="&lt;Path&gt;" class="fill-custom-400 dark:fill-custom-700" d="m101.5 299.9l-1.3-3.1v-315.3l1.3 3.1z"></path>
                        <path id="&lt;Path&gt;" class="fill-custom-500/50 dark:fill-custom-600/50" d="m109.3 307.8l-1.3-3.1v-315.4l1.3 3.1z"></path>
                    </g>
                </svg>
            </div>

            <div class="absolute hidden -rotate-180 opacity-50 ltr:-right-16 rtl:-left-16 -bottom-10 md:block">
                <svg version="1.2" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 125 316" width="125" height="316">
                    <title>&lt;Group&gt;</title>
                    <g id="&lt;Group&gt;">
                        <path id="&lt;Path&gt;" class="fill-custom-100/50 dark:fill-custom-950/50" d="m23.4 221.8l-1.3-3.1v-315.3l1.3 3.1z"></path>
                        <path id="&lt;Path&gt;" class="fill-custom-100 dark:fill-custom-950" d="m31.2 229.6l-1.3-3.1v-315.3l1.3 3.1z"></path>
                        <path id="&lt;Path&gt;" class="fill-custom-200/50 dark:fill-custom-900/50" d="m39 237.4l-1.3-3.1v-315.3l1.3 3.1z"></path>
                        <path id="&lt;Path&gt;" class="fill-custom-200/75 dark:fill-custom-900/75" d="m46.8 245.2l-1.3-3.1v-315.3l1.3 3.1z"></path>
                        <path id="&lt;Path&gt;" class="fill-custom-200 dark:fill-custom-900" d="m54.6 253.1l-1.3-3.1v-315.4l1.3 3.1z"></path>
                        <path id="&lt;Path&gt;" class="fill-custom-300/50 dark:fill-custom-800/50" d="m62.4 260.9l-1.2-3.1v-315.4l1.2 3.1z"></path>
                        <path id="&lt;Path&gt;" class="fill-custom-300/75 dark:fill-custom-800/75" d="m70.3 268.7l-1.3-3.1v-315.4l1.3 3.1z"></path>
                        <path id="&lt;Path&gt;" class="fill-custom-300 dark:fill-custom-800" d="m78.1 276.5l-1.3-3.1v-315.3l1.3 3.1z"></path>
                        <path id="&lt;Path&gt;" class="fill-custom-400/50 dark:fill-custom-700/50" d="m85.9 284.3l-1.3-3.1v-315.3l1.3 3.1z"></path>
                        <path id="&lt;Path&gt;" class="fill-custom-400/75 dark:fill-custom-700/75" d="m93.7 292.1l-1.3-3.1v-315.3l1.3 3.1z"></path>
                        <path id="&lt;Path&gt;" class="fill-custom-400 dark:fill-custom-700" d="m101.5 299.9l-1.3-3.1v-315.3l1.3 3.1z"></path>
                        <path id="&lt;Path&gt;" class="fill-custom-500/50 dark:fill-custom-600/50" d="m109.3 307.8l-1.3-3.1v-315.4l1.3 3.1z"></path>
                    </g>
                </svg>
            </div>

            <div class="mb-0 w-screen lg:mx-auto lg:w-[500px] card shadow-lg border-none shadow-slate-100 relative">
                <div class="!px-10 !py-12 card-body">
                    <a href="#!">
                        <h1 class="text-center">Certainty Factor</h1>                
                    </a>
                    <div class="mt-8 text-center">
                        <h4 class="mb-1 text-custom-500 dark:text-custom-500">Selamat Datang!</h4>
                        <p class="text-slate-500 dark:text-zink-200">sistem pakar pendeteksi kecanduan game online</p>
                    </div>
                    @if(session('error'))
                    <div class="alert alert-danger">
                        {{ session('error') }}
                    </div>
                    @endif
                    <form action="/logins" method="POST" class="mt-10" id="signInForm">
                        @csrf
                        <div class="mb-3">
                            <label for="email" class="inline-block mb-2 text-base font-medium">Email</label>
                            <input type="email" name="email" id="email" class="form-input border-slate-200 dark:border-zink-500 focus:outline-none focus:border-custom-500 disabled:bg-slate-100 dark:disabled:bg-zink-600 disabled:border-slate-300 dark:disabled:border-zink-500 dark:disabled:text-zink-200 disabled:text-slate-500 dark:text-zink-100 dark:bg-zink-700 dark:focus:border-custom-800 placeholder:text-slate-400 dark:placeholder:text-zink-200" placeholder="Enter email" required>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="inline-block mb-2 text-base font-medium">Password</label>
                            <input type="password" name="password" id="password" class="form-input border-slate-200 dark:border-zink-500 focus:outline-none focus:border-custom-500 disabled:bg-slate-100 dark:disabled:bg-zink-600 disabled:border-slate-300 dark:disabled:border-zink-500 dark:disabled:text-zink-200 disabled:text-slate-500 dark:text-zink-100 dark:bg-zink-700 dark:focus:border-custom-800 placeholder:text-slate-400 dark:placeholder:text-zink-200" placeholder="Enter password" required>
                        </div>
                        <h6>Lupa Password?<a style="color: blue" href="/">Klik disini</a></h6>
                        <div class="mt-10">
                            <button type="submit" class="w-full text-white btn bg-custom-500 border-custom-500 hover:text-white hover:bg-custom-600 hover:border-custom-600 focus:text-white focus:bg-custom-600 focus:border-custom-600 focus:ring focus:ring-custom-100 active:text-white active:bg-custom-600 active:border-custom-600 active:ring active:ring-custom-100 dark:ring-custom-400/20">Masuk</button>
                        </div>
                    </form>
                    <center><p class="mt-3 mb-3">atau</p></center>
                        <div>
                            <a href="/registerIndexUser"><button type="submit" class="w-full text-white btn bg-red-500 border-red-500 hover:text-white hover:bg-red-600 hover:border-red-600 focus:text-white focus:bg-red-600 focus:border-red-600 focus:ring focus:ring-red-100 active:text-white active:bg-red-600 active:border-red-600 active:ring active:ring-red-100 dark:ring-red-400/20">Daftar</button></a>
                        </div>
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
