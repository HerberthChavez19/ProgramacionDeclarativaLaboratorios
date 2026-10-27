$ErrorActionPreference = "Stop"
$raiz = $PSScriptRoot
$jar = Join-Path $raiz "lib\tuprolog-3.3.0.jar"
$out = Join-Path $raiz "out"

New-Item -ItemType Directory -Force -Path $out | Out-Null
Copy-Item (Join-Path $raiz "src\calculo.pl") $out -Force

javac -encoding UTF-8 -cp $jar -d $out (Join-Path $raiz "src\Connection.java") (Join-Path $raiz "src\Window.java")
java -cp "$out;$jar" Window
