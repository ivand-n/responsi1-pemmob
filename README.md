

## Identitas Mahasiswa

Nama: Ivan Darmawan
NIM: H1D022042  
Shift : B (krs = I)

## Video Demo Aplikasi

https://private-user-images.githubusercontent.com/236172895/505731773-2557bcd7-710d-47dc-a08e-30fcff22a43f.mp4?jwt=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NjE0ODcwMzMsIm5iZiI6MTc2MTQ4NjczMywicGF0aCI6Ii8yMzYxNzI4OTUvNTA1NzMxNzczLTI1NTdiY2Q3LTcxMGQtNDdkYy1hMDhlLTMwZmNmZjIyYTQzZi5tcDQ_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUxMDI2JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MTAyNlQxMzUyMTNaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT05OTY2NzhkOWNmM2IzNzhkOWUwM2NiM2VlYjA1ZDJkNWU0Zjc4NTMzYTRkZjkxMzI3YmVjZmNjMmU5YmIyZTQzJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.hFOHLKjWjkGWRXE6FSyTmKp0JVn0VSBPajd8O2Gl6d4

## Penjelasan Alur Data

Aplikasi ini memanfaatkan Football Data API (`https://api.football-data.org/v4/`) untuk menampilkan informasi Real Betis (Team ID 285), dengan konfigurasi kunci API dan base URL disimpan dalam `Constants.kt`. Setiap permintaan API menggunakan Retrofit dan OkHttpClient yang menambahkan header `X-Auth-Token` untuk autentikasi. Ketika pengguna mengakses halaman Coach atau Squad, aplikasi secara asinkron menjalankan permintaan GET ke *endpoint* `/teams/{id}` menggunakan Kotlin Coroutines dalam `lifecycleScope`. Respons JSON kemudian diurai menggunakan Gson menjadi data class `TeamResponse` yang berisi data tunggal *coach* dan daftar *squad* (*Player*). Data *coach* langsung ditampilkan di `TextView`, sementara data *squad* disajikan di `RecyclerView` melalui `PlayerAdapter`, yang menerapkan kode warna berdasarkan posisi pemain (hijau untuk *goalkeeper*, biru untuk *defender*, oranye untuk *midfielder*, dan merah untuk *forward*). Interaksi pengguna memungkinkan tampilan `PlayerDetailFragment` di bagian bawah layar saat mengklik kartu pemain, yang dapat ditutup melalui area kosong atau tombol kembali. Seluruh halaman dilengkapi dengan *error handling* yang menampilkan tata letak *error* dengan pesan dan tombol *retry* jika terjadi kegagalan jaringan atau respons tidak valid, memungkinkan pengguna untuk mengulang proses pemanggilan API dari Tahap 2.
