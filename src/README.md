# 🎮 Auto Lobby AFK Mod

Minecraft 1.21.11 Fabric Mod - Automatische Rückkehr zur Lobby nach AFK.

## ⚡ Quick Facts

- **Minecraft Version**: 1.21.11
- **Mod Loader**: Fabric
- **Java Version**: 21+
- **File Size**: ~50KB
- **Dependencies**: Fabric API

---

## 🎯 Was macht die Mod?

**Automatisch zur Lobby gehen wenn du zu lange AFK stehst:**

- 🕐 **Nach 3 Minuten inaktiv**: 
  - ⚠️ Rote Warnung wird angezeigt (mittig im Bildschirm)
  - 🔊 Warnsignal Sound spielt
  - Text: "Du wirst in x Minuten in die Lobby gesendet!"

- 🕐 **Nach 5 Minuten inaktiv**:
  - 📤 Automatisch `/lobby` Befehl wird ausgeführt
  - 🎮 Du wirst zur Lobby teleportiert

**Input-Tracking:**
- ✅ WASD + Space = Bewegung (Timer resettet)
- ❌ Maus drehen = KEINE Bewegung (wird ignoriert)

---

## 📥 Installation

### 1. JAR-Datei
```
C:\Users\tonyw\AppData\Roaming\.minecraft\labymod-neo\modpacks\opsucht_1774477159972\fabric\1.21.11\mods\
```

### 2. Config (optional)
```
C:\Users\tonyw\AppData\Roaming\.minecraft\labymod-neo\modpacks\opsucht_1774477159972\fabric\1.21.11\config\
```
Dateiname: `autolobby.json`

### 3. Minecraft starten
Mod wird beim Start geladen!

---

## 🛠️ Kompilierung (IntelliJ IDEA)

1. **Siehe**: `SETUP_GUIDE.md` für detaillierte Anleitung
2. **Quick**: 
   ```bash
   ./gradlew build
   ```
3. **JAR Location**: `build/libs/autolobby-1.0.0.jar`

---

## ⚙️ Konfiguration

`autolobby.json` im Config-Ordner:

```json
{
  "enabled": true,                    // Mod aktiv?
  "warning_time_seconds": 180,        // 3 Minuten
  "logout_time_seconds": 300,         // 5 Minuten
  "play_sound": true,                 // Sound aktiviert?
  "warning_color": "#FF3333",         // Rot
  "warning_text_color": "#FFFFFF"     // Weiß
}
```

---

## 📝 Dateien in diesem Package

```
├── SETUP_GUIDE.md              ← START HIER! (IntelliJ Setup)
├── README.md                   ← Diese Datei
├── build.gradle                ← Gradle Build-Config
├── gradle.properties            ← Projekt-Properties
├── settings.gradle              ← Gradle Settings
├── fabric.mod.json              ← Mod Manifest
├── autolobby.json               ← Config Template
│
└── Java Source Files:
    ├── com.opsucht.autolobby.AutoLobbyMod.java       ← Main Entry Point
    ├── com.opsucht.autolobby.AFKTimer.java           ← Timer-Logik (3min + 5min)
    ├── com.opsucht.autolobby.MovementTracker.java    ← W/A/S/D/Space Tracking
    └── com.opsucht.autolobby.WarningHUD.java         ← Warning Display + Sound
```

---

## 🚀 Schritte zum Kompilieren

1. **Alle Dateien herunterladen**
2. **IntelliJ IDEA öffnen**
3. **Open Project** → Wähle den `autolobby` Ordner
4. **Warte auf Gradle Sync** (oben "Sync Now" Button)
5. **Terminal**: `./gradlew build`
6. **JAR ist fertig**: `build/libs/autolobby-1.0.0.jar`
7. **Copy zu**: `fabric/1.21.11/mods/`
8. **Minecraft starten** → Mod lädt!

---

## 🎮 Gameplay

**Auf CityBuild:**
1. Du stehst AFK → Timer startet
2. Nach 3 Min → **Warnung kommt** (rot, Sound)
3. Nach 5 Min → **Du wirst zur Lobby teleportiert** (`/lobby`)
4. Wenn du dich bewegst (WASD/Space) → **Timer resettet** + Warning verschwindet

**In Lobby:**
- Mod bleibt aktiv
- Du kannst AFK stehen ohne gekickt zu werden
- Wenn du wieder zum CB gehst → Timer startet neu

---

## 🔧 Troubleshooting

| Problem | Lösung |
|---------|--------|
| JAR kompiliert nicht | → Siehe SETUP_GUIDE.md Troubleshooting |
| Mod lädt nicht | → JAR im falschen Folder? → `fabric/1.21.11/mods/` prüfen |
| Sound spielt nicht | → Minecraft Audio prüfen → Fabric API installiert? |
| Timer funktioniert nicht | → Minecraft neustarten → Logs prüfen |

---

## 📞 Support

- **Logs**: `C:\Users\tonyw\AppData\Roaming\.minecraft\logs\latest.log`
- **IntelliJ Help**: Gradle Tasks → Build für detaillierte Fehler
- **Mod nicht laden?**: Fabric Loader updaten

---

## 📄 Lizenz

MIT License - Frei nutzbar und modifizierbar!

---

**Version**: 1.0.0  
**Minecraft**: 1.21.11  
**Mod Loader**: Fabric  

🎉 **Viel Spaß mit der Mod!**
