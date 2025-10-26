

## Identitas Mahasiswa

Nama: Ivan Darmawan
NIM: H1D022042  
Shift : B (krs = I)

## Video Demo Aplikasi

https://github.com/ivand-n/responsi1-pemmob/issues/1#issuecomment-3448547059


## Penjelasan Alur Data

Aplikasi ini memanfaatkan Football Data API (`https://api.football-data.org/v4/`) untuk menampilkan informasi Real Betis (Team ID 285), dengan konfigurasi kunci API dan base URL disimpan dalam `Constants.kt`. Setiap permintaan API menggunakan Retrofit dan OkHttpClient yang menambahkan header `X-Auth-Token` untuk autentikasi. Ketika pengguna mengakses halaman Coach atau Squad, aplikasi secara asinkron menjalankan permintaan GET ke *endpoint* `/teams/{id}` menggunakan Kotlin Coroutines dalam `lifecycleScope`. Respons JSON kemudian diurai menggunakan Gson menjadi data class `TeamResponse` yang berisi data tunggal *coach* dan daftar *squad* (*Player*). Data *coach* langsung ditampilkan di `TextView`, sementara data *squad* disajikan di `RecyclerView` melalui `PlayerAdapter`, yang menerapkan kode warna berdasarkan posisi pemain (hijau untuk *goalkeeper*, biru untuk *defender*, oranye untuk *midfielder*, dan merah untuk *forward*). Interaksi pengguna memungkinkan tampilan `PlayerDetailFragment` di bagian bawah layar saat mengklik kartu pemain, yang dapat ditutup melalui area kosong atau tombol kembali. Seluruh halaman dilengkapi dengan *error handling* yang menampilkan tata letak *error* dengan pesan dan tombol *retry* jika terjadi kegagalan jaringan atau respons tidak valid, memungkinkan pengguna untuk mengulang proses pemanggilan API dari Tahap 2.
