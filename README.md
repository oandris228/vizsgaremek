# 💻 Letöltési Útmutató az Adatbázishoz / Backendhez / Frontendhez

📖 A részletesebb, szebb dokumentáció elérhető a Wiki-n:  
🔗 [Weboldal Felhasználói Dokumentáció](https://github.com/oandris228/vizsgaremek/wiki/Weboldal-Felhaszn%C3%A1l%C3%B3i-Dokument%C3%A1ci%C3%B3)

---

## ⚙️ XAMPP Elindítása

> **Ha nincs telepítve a XAMPP:**
1. Töltse le a [XAMPP](https://www.apachefriends.org/index.html) alkalmazást.
2. Telepítés után indítsa el az **Apache** és **MySQL** modulokat.

⚠️ **Megjegyzés:**  
Más adatbázis-kezelő rendszert is lehet használni, de ezek konfigurálása nem része ennek az útmutatónak.

---

## 🛢️ Adatbázis Létrehozása és Futtatása

- A **Backend** automatikusan legenerálja az adatbázist.
- **Tesztadatok használatához:**
  - Importálja be az sql fájlt a repo-ból a MySQL adatbázis-kezelőbe **a backend futtatása előtt**.

---

## 🔧 Backend Letöltése és Futtatása

1. Töltse le ZIP formátumban vagy klónozza a repository **`web`** branch-ét:
   ```bash
   git clone -b web https://github.com/oandris228/vizsgaremek.git
   ```

2. Nyissa meg a kicsomagolt mappát egy kódszerkesztőben.

3. Nevezze át a `.env.example` fájlt `.env`-re és adja meg benne az adatbázis hivatkozását.

4. Terminálban adja ki az alábbi parancsokat:

   ```bash
   cd backend
   npm install
   npx prisma db push
   npm run start        # vagy: npm run start:debug
   ```

   > 🧠 Ha más csomagkezelőt (pl. yarn) használ, kérjük alkalmazza annak megfelelő parancsait.

---

## 🌐 Frontend Modulok Letöltése és Futtatása

1. Terminálban lépjen be a frontend könyvtárba:

   ```bash
   cd frontend
   npm install
   npm run dev
   ```

---

Ha bármi kérdésed van, nézd meg a [Wiki oldalt](https://github.com/oandris228/vizsgaremek/wiki), vagy nyiss egy `issue`-t a repositoryban. 😊
