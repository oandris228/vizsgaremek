# Java asztali
# Kezdés
## Az asztali alkalmazás elindításához a következő lépések szükségesek:
### XAMPP elindítása
ha nincs, telepítse le a XAMPP alkalmazást és indítsa el az Apache és a MySQL modulokat (ha másfajta adatbázis kezelő rendszert szeretne alkalmazni megvan rá a lehetőség, viszont ez a dokumentáció nem tér ki ezekre az esetekre és azt magától kell megoldania)
### Adatbázis létrehozása és futtatása
A Backend automatikusan legenerálja az adatbázist, viszont ha teszt adatokat is szeretne használni, a backend futtatása előtt importálja be a dump sql fájl-t az adatbázis kezelőjébe.
### Backend letöltése és futtatása
* töltse le zip formátumban, vagy klónozza a repo web branch-ét
* a kicsomagolt mappát nyissa meg egy kódszerkesztő programban és a .env example fájl-t .env-re nevezze át, majd az adja meg az adatbázis hivatkozását
* nyissa meg a kicsomagolt mappát egy terminálban és írja be a következő parancsokat:
1. `cd backend`
1. `npm i` (ha más csomagkezelőt használ akkor kérem használja annak a megfelelőjét)
1. `npx prisma db push`
1. `npm run start` vagy `npm run start:debug`

### Asztali alkalmazás letöltése és futtatása
* töltse le zip formátumban, vagy klónozza a repo java-asztali branch-ét
* a kicsomagolt mappát nyissa meg egy kódszerkesztő programban
* végül futtassa le a HelloApplication.javat

## Felhasználói dokumentáció
### Főbb funkciók
* Adattáblák megtekintése és szerkesztése
* A Form menüpontokkal új adatok felvétele

### Indítás:

* Az alkalmazás indítása után megjelenik a főképernyő, ott tud navigálni különböző táblák és formok közöztt

## Fejlesztői dokumentáció
### Projekt struktúra
* vizsgaremek/
* ├── src/
* │   ├── main/
* │   │   ├── java/            # Java forráskódok
* │   │   └── resources/       # Erőforrás fájlok
* │   └── test/                # Teszt kódok
* └──  pom.xml                 # Maven konfiguráció

### Függőségek
* A projekt a pom.xml alapján hivja meg függőségeket (ne változtassa kivéve ha tudja mit csinál)

### Kód módosítása:
* A forráskód a src/main/java mappában található
* Az erőforrások a src/main/resources mappában

***
### Ha bármi egyéb problémaja lenne keresse **szankacsanad** fejlesztőt













