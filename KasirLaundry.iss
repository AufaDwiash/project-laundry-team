; ==============================
;  Inno Setup Script - Kasir Laundry
; ==============================

[Setup]
AppName=Kasir Laundry
AppVersion=1.0
DefaultDirName={pf}\Kasir Laundry
DefaultGroupName=Kasir Laundry
OutputDir=.
OutputBaseFilename=KasirLaundry_Setup
Compression=lzma
SolidCompression=yes


[Tasks]
Name: "desktopicon"; Description: "Buat shortcut di Desktop"; GroupDescription: "Shortcut:"; Flags: unchecked

[Files]
; === SESUAIKAN BAGIAN INI DENGAN STRUKTUR dist KAMU ===
; Misal struktur kamu:
; dist\
;   ├─ Mitra.jar
;   └─ lib\
;       ├─ mysql-connector-j-9.5.0.jar
;       ├─ itextpdf-5.5.13.3.jar
;       └─ jcalendar-1.4.jar

; File utama JAR
Source: "dist\Mitra.jar"; DestDir: "{app}"; Flags: ignoreversion

; Folder lib (semua jar di dalamnya)
Source: "dist\lib\*"; DestDir: "{app}\lib"; Flags: ignoreversion recursesubdirs createallsubdirs

; (Opsional) kalau kamu punya file lain, misal README:
; Source: "dist\README.TXT"; DestDir: "{app}"; Flags: ignoreversion

[Icons]
; Shortcut di Start Menu
Name: "{group}\Kasir Laundry"; Filename: "{cmd}"; Parameters: "/c java -jar ""{app}\Mitra.jar"""; WorkingDir: "{app}"

; Shortcut di Desktop (kalau user pilih task desktopicon)
Name: "{commondesktop}\Kasir Laundry"; Filename: "{cmd}"; Parameters: "/c java -jar ""{app}\Mitra.jar"""; WorkingDir: "{app}"; Tasks: desktopicon

[Run]
; Jalankan aplikasi setelah install (opsional)
Filename: "{cmd}"; Parameters: "/c java -jar ""{app}\Mitra.jar"""; WorkingDir: "{app}"; Flags: nowait postinstall skipifsilent
