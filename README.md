# Taller 2 POO Dylan Ordóñez — Juego Pokémon

Juego por consola basado en el universo Pokémon, desarrollado en Java como parte del Taller 2 de Programación Orientada a Objetos. El jugador puede explorar zonas para capturar Pokémon, retar gimnasios, desafiar al Alto Mando y gestionar su equipo.

---

## Integrantes

| Nombre | RUT | Usuario GitHub |
|---|---|---|
| Dylan Nicolás Ordóñez Miranda | 21.387.801-8 | DylanZaur |

---

## Estructura del proyecto

```
Taller2POO/
├── src/
│   └── taller02/
│       ├── Juego.java              # Clase principal (main) y lógica de menús
│       ├── Pokemon.java            # Clase Pokemon con atributos y stats
│       ├── Jugador.java            # Clase Jugador con equipo y medallas
│       ├── Habitat.java            # Clase Habita con zonas de captura con generación aleatoria
│       ├── Gimnasio.java           # Clase Gimnasio con líder y sus Pokémon
│       ├── MiembroAltoMando.java   # Clase Miembro del Alto Mando con sus Pokémon
│       ├── TablaTipos.java         # Clase para Matriz de efectividad entre tipos
│       └── GestorDeArchivos.java   # Clase para Lectura y escritura de archivos .txt
├── Pokedex.txt                     # Lista completa de Pokémon disponibles
├── Habitats.txt                    # Zonas de exploración
├── Gimnasios.txt                   # Datos de los 8 gimnasios y sus lídere
├── Alto Mando.txt                  # Datos de los miembros del Alto Mando
├── Registros.txt                   # Partida guardada del jugador
├── MODELO DE DOMINIO.pdf           # Diagrama del modelo de dominio
├── DiagramaClases.pdf              # Diagrama de clases UML
└── README.md
```

---

## Instrucciones de ejecución

1. Clonar el repositorio
2. Abrir el proyecto en Eclipse como proyecto Java existente.
3. Verificar que los archivos `.txt` estén en la raíz del proyecto (al mismo nivel que la carpeta `src`).
4. Ejecutar la clase `Juego.java` como aplicación Java.
5. Luego de ejecutar la clase, seleccionar la 2da opcion "Nueva partida".
6. Después seleccionar la 2da opcion nuevamente para atrapar pokemons.
7. Finalmente cuando tenemos nuestro equipo listo retar tanto los gimnasios como al alta mando.

---

## Funcionalidades

- **Nueva Partida / Continuar:** crea o carga una partida desde `Registros.txt`
- **Revisar equipo:** muestra los primeros 6 Pokémon del jugador con sus stats
- **Salir a capturar:** explora zonas y captura Pokémon según porcentaje de aparición
- **Acceso al PC:** intercambia Pokémon entre el equipo activo y el PC
- **Retar gimnasio:** combate contra los 8 líderes en orden, aplicando tabla de tipos
- **Desafiar Alto Mando:** requiere las 8 medallas, combates consecutivos sin retorno
- **Curar Pokémon:** restaura el estado de todos los Pokémon a "Vivo"
- **Guardar / Guardar y Salir:** persiste el estado actual en `Registros.txt`
