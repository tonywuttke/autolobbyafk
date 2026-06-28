# Auto Lobby AFK Mod - Setup & Build Guide

## 📋 Voraussetzungen

- ✅ IntelliJ IDEA Community Edition (kostenlos)
- ✅ Java 21+ installiert
- ✅ Git (optional, aber empfohlen)

---

## 🚀 STEP 1: Projekt-Struktur erstellen

1. **IntelliJ öffnen**
2. **New Project** → Choose "Gradle"
3. **Create new Gradle Project** mit diesen Einstellungen:
   - **Project Name**: `autolobby`
   - **Location**: Irgendwo (z.B. `C:\MinecraftMods\autolobby`)
   - **Gradle DSL**: Kotlin oder Groovy (egal)

4. **Projekt öffnen** → Warte auf Gradle Sync

---

## 📁 STEP 2: Dateien erstellen

### Ordnerstruktur aufbauen:

```
autolobby/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/opsucht/autolobby/
│   │   │       ├── com.opsucht.autolobby.AutoLobbyMod.java
│   │   │       ├── com.opsucht.autolobby.AFKTimer.java
│   │   │       ├── com.opsucht.autolobby.MovementTracker.java
│   │   │       └── com.opsucht.autolobby.WarningHUD.java
│   │   └── resources/
│   │       ├── fabric.mod.json
│   │       └── assets/autolobby/
│   │           └── icon.png (optional)
│   └── test/
├── build.gradle
├── gradle.properties
└── settings.gradle
```

### Dateien erstellen:

1. **Rechtsklick auf `src/main/java`** → New → Package
   - Name: `com.opsucht.autolobby`

2. **Rechtsklick auf Package** → New → Java Class
   - **com.opsucht.autolobby.AutoLobbyMod.java** → Paste Code
   - **com.opsucht.autolobby.AFKTimer.java** → Paste Code
   - **com.opsucht.autolobby.MovementTracker.java** → Paste Code
   - **com.opsucht.autolobby.WarningHUD.java** → Paste Code

3. **Rechtsklick auf `src/main/resources`** → New → File
   - Name: `fabric.mod.json` → Paste Code

---

## ⚙️ STEP 3: build.gradle & gradle.properties

**Existing `build.gradle` ERSETZEN** mit dem bereitgestellten Code

**Existing `gradle.properties` ERSETZEN** mit dem bereitgestellten Code

---

## 🔧 STEP 4: Gradle Sync

1. **IntelliJ sollte oben einen "Sync Now" Button zeigen**
2. **Klick drauf** → Wartet auf Download von Dependencies
3. **In der Konsole sollte am Ende stehen: "BUILD SUCCESSFUL"**

Falls nicht:
- Rechtsklick auf `build.gradle` → Reload Gradle Project
- Oder: Menu → Build → Rebuild Project

---

## 🛠️ STEP 5: JAR kompilieren

### Option A - Über Gradle GUI:

1. **Rechts oben auf "Gradle"** Symbol klicken
2. **Navigate zu**: `autolobby` → `Tasks` → `build`
3. **Doppelklick auf `build`** 
4. **JAR wird erstellt in**: `build/libs/autolobby-1.0.0.jar`

### Option B - Über Terminal:

1. **Terminal öffnen** in IntelliJ (unten)
2. **Command eingeben:**
   ```bash
   ./gradlew build
   ```
3. **Warten...** (~1-2 Min beim ersten Mal)
4. **BUILD SUCCESSFUL** → JAR ist fertig!

---

## 📦 STEP 6: JAR-Datei verwenden

1. **JAR-Datei finden:**
   ```
   C:\Dein-Projekt\autolobby\build\libs\autolobby-1.0.0.jar
   ```

2. **Kopieren zu LabyMod Mods:**
   ```
   C:\Users\tonyw\AppData\Roaming\.minecraft\labymod-neo\modpacks\opsucht_1774477159972\fabric\1.21.11\mods\
   ```

3. **Config erstellen:**
   - Gehe zu: `C:\Users\tonyw\AppData\Roaming\.minecraft\labymod-neo\modpacks\opsucht_1774477159972\fabric\1.21.11\config\`
   - Erstelle neue Datei: `autolobby.json`
   - Copy die Config-JSON rein

---

## 🎮 STEP 7: Minecraft Starten

1. **Minecraft starten** (via LabyMod)
2. **Auf OPSUCHT Citybuild joinen**
3. **AFK stehen**
4. **Nach 3 Min:** Warnung sollte erscheinen + Sound
5. **Nach 5 Min:** Auto `/lobby` Befehl

---

## ❌ TROUBLESHOOTING

### "BUILD FAILED: Cannot resolve symbol..."
- → Gradle Sync nicht gemacht
- → Klick "Sync Now" wenn es oben auftaucht

### "Java 21 not found"
- → Java 21 installieren von java.com
- → IntelliJ → Settings → Project Structure → JDK auf Java 21 setzen

### "JAR existiert aber funktioniert nicht"
- → Config nicht im richtigen Folder
- → Minecraft/Mod neu starten
- → Schau in Minecraft Logs (`.minecraft/logs/latest.log`)

### "Sound spielt nicht"
- → Minecraft Audio-Einstellungen prüfen
- → Fabric API richtig installiert?

### "Timer lädt nicht"
- → Mod-JAR im falschen Folder?
- → Check: `fabric/1.21.11/mods/` existiert?

---

## 🎯 ERGEBNIS

Nach erfolgreichem Build solltest du haben:

✅ `autolobby-1.0.0.jar` → In den `mods/` Folder  
✅ `autolobby.json` → In den `config/` Folder  
✅ Mod lädt beim Spielstart  
✅ Nach 3 Min inaktiv: Warnung + Sound  
✅ Nach 5 Min inaktiv: Auto `/lobby`  

---

## 📝 Anpassungen (Optional)

Wenn du die Zeiten/Farben ändern willst:

### Java Code ändern:
- **com.opsucht.autolobby.AFKTimer.java** → Zeilen mit `WARNING_TIME` und `LOGOUT_TIME`
- **com.opsucht.autolobby.WarningHUD.java** → Farb-Codes ändern (z.B. `0xDD3333FF`)

### Dann neu kompilieren:
```bash
./gradlew clean build
```

---

**Viel Erfolg beim Bauen! 🚀**

Bei Fragen: Discord/DM
