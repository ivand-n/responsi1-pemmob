

## Identitas Mahasiswa

Nama: Ivan Darmawan
NIM: H1D022042  
Shift : B (krs = I)

## Video Demo Aplikasi

[https://github.com/ivand-n/responsi1-pemmob/issues/1#issuecomment-3448547059](https://private-user-images.githubusercontent.com/236172895/505730342-2ccfaebc-8363-462b-8f99-76afb4fee169.mp4?jwt=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NjE0ODYxNTUsIm5iZiI6MTc2MTQ4NTg1NSwicGF0aCI6Ii8yMzYxNzI4OTUvNTA1NzMwMzQyLTJjY2ZhZWJjLTgzNjMtNDYyYi04Zjk5LTc2YWZiNGZlZTE2OS5tcDQ_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjUxMDI2JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI1MTAyNlQxMzM3MzVaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT0zM2Q0Y2FmOTkwNmNjOGRkNjFjNWE1M2QzNzU0NTE1ZTA2NWM5NjRiZjM2MTQ4OTlhNzFmNGEyYjdkMWJjNTJkJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCJ9.1v-XUq1nT5VXeuoAlw4NNPm88QvPfnUZCFzsP8bzxQM)


## Penjelasan Alur Data

Aplikasi ini memanfaatkan Football Data API (`https://api.football-data.org/v4/`) untuk menampilkan informasi Real Betis (Team ID 285), dengan konfigurasi kunci API dan base URL disimpan dalam `Constants.kt`. Setiap permintaan API menggunakan Retrofit dan OkHttpClient yang menambahkan header `X-Auth-Token` untuk autentikasi. Ketika pengguna mengakses halaman Coach atau Squad, aplikasi secara asinkron menjalankan permintaan GET ke *endpoint* `/teams/{id}` menggunakan Kotlin Coroutines dalam `lifecycleScope`. Respons JSON kemudian diurai menggunakan Gson menjadi data class `TeamResponse` yang berisi data tunggal *coach* dan daftar *squad* (*Player*). Data *coach* langsung ditampilkan di `TextView`, sementara data *squad* disajikan di `RecyclerView` melalui `PlayerAdapter`, yang menerapkan kode warna berdasarkan posisi pemain (hijau untuk *goalkeeper*, biru untuk *defender*, oranye untuk *midfielder*, dan merah untuk *forward*). Interaksi pengguna memungkinkan tampilan `PlayerDetailFragment` di bagian bawah layar saat mengklik kartu pemain, yang dapat ditutup melalui area kosong atau tombol kembali. Seluruh halaman dilengkapi dengan *error handling* yang menampilkan tata letak *error* dengan pesan dan tombol *retry* jika terjadi kegagalan jaringan atau respons tidak valid, memungkinkan pengguna untuk mengulang proses pemanggilan API dari Tahap 2.
