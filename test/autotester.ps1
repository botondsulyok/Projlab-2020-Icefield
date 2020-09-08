# Egy teszteset tesztelését végző függvény
function test([string]$testname) {
    # Input és output fájl beolvasása
    $infile = "$($testname).in"
    $expfile = "$($testname).out"
    # Ha hiányzik bármelyik, hibát dob
    if ((-Not (Test-Path $infile)) -Or (-Not (Test-Path $expfile))) {
        Write-Host "Error: Test `"$($testname)`" not found" -ForegroundColor red
        return 0
    }
    # Teszt lefuttatása
    $in = Get-Content $infile
    $out = echo $in | java -cp ../out/production/projlab-2020 hu.minesweepers.Main
    $exp = Get-Content $expfile
    # Kimenetek összehasonlítása
    if(-Not (Compare-Object $out $exp)) {
        # Megegyezik a két kimenet
        Write-Host "Test `"$($testname)`" Passed" -ForegroundColor green
        return 1
    }
    else {
        # Eltér a két kimenet
        Write-Host "Test `"$($testname)`" Failed" -ForegroundColor red
        # Különbségek kiíratása
        Write-Host "Expected:" -ForegroundColor yellow
        foreach($line in $exp) {
            Write-Host $line
        }
        Write-Host "Got:" -ForegroundColor yellow
        foreach($line in $out) {
            Write-Host $line
        }
        return 0
    }
}

# Az összes tesztet megkeresi és lefuttatja
function testall() {
    # Tesztesetek megkeresése
    $testfiles = dir -Filter *.in | %{$_.Name}
    # Helyesen lefutó teszteket számolja
    $count = 0 
    # Tesztesetek lefuttatása
    foreach ($file in $testfiles) {
        if (test($file.Trim(".in"))) {
            $count++
        }
    }
    # Sikeresen lefutott tesztek számának kiírása
    Write-Host "$($count)/$($testfiles.Count) Tests passed" -ForegroundColor yellow
}

# Elvégzi a forráskód compile-olását, amennyiben szükséges
if (-Not (Test-Path ../out/production/projlab-2020/hu/minesweepers/Main.class)) {
    dir -Path ../src -Filter *.java -Recurse | %{$_.FullName} | Out-File sources.txt -Encoding ascii
    javac `@sources.txt -d ../out/production/projlab-2020
    Remove-Item sources.txt
}

# Argumentumok kezelése
if (($args.Count -Eq 1) -And ($args[0] -Eq "testall")) {
    testall
}
elseif (($args.Count -Eq 2) -And($args[0] -Eq "test")) {
    test $args[1] | Out-Null # A pipe miatt nem írodik ki a return
}
else {
    Write-Host "Usage:"
    Write-Host "testall - Run all tests"
    Write-Host "test <test name> - Run one test"
}