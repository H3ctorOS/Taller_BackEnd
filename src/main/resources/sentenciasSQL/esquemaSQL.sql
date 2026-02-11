-- TABLAS
    CREATE TABLE IF NOT EXISTS vehiculos (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            matricula TEXT UNIQUE NOT NULL,
            modelo TEXT,
            marca TEXT,
            cod_estado TEXT NOT NULL,
            observaciones TEXT,

            FOREIGN KEY (cod_estado) REFERENCES estados(codigo)
     );


    CREATE TABLE IF NOT EXISTS mecanicos (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            dni TEXT NOT NULL,
            nombre TEXT NOT NULL,
            cod_estado TEXT NOT NULL,
            observaciones TEXT,

            FOREIGN KEY (cod_estado) REFERENCES estados(codigo)
    );


    CREATE TABLE IF NOT EXISTS clientes (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            dni TEXT NOT NULL,
            nombre TEXT NOT NULL,
            apellidos TEXT,
            direccion TEXT,
            telefono INTEGER,
            email TEXT,
            cod_estado TEXT NOT NULL,
            observaciones TEXT,

            FOREIGN KEY (cod_estado) REFERENCES estados(codigo)
    );


    CREATE TABLE IF NOT EXISTS reparaciones (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            concepto TEXT NOT NULL,
            vehiculo_id INTEGER NOT NULL,
            mecanico_id INTEGER,
            fechaInicio DATE,
            fechaFin DATE,
            observaciones TEXT,

            FOREIGN KEY (vehiculo_id) REFERENCES vehiculos(id),
            FOREIGN KEY (mecanico_id) REFERENCES mecanicos(id)
    );


    CREATE TABLE IF NOT EXISTS propietarios (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            vehiculo_id INTEGER NOT NULL,
            cliente_id INTEGER NOT NULL,
            fechaAlta DATE NOT NULL,
            fechaBaja DATE,
            cod_estado TEXT NOT NULL,
            observaciones TEXT,

            FOREIGN KEY (vehiculo_id) REFERENCES vehiculos(id),
            FOREIGN KEY (cliente_id) REFERENCES clientes(id),
            FOREIGN KEY (cod_estado) REFERENCES estados(codigo)
    );

    CREATE TABLE IF NOT EXISTS citas (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            vehiculo_id INTEGER NOT NULL,
            concepto TEXT NOT NULL,
            fechaInicio DATE NOT NULL,
            fechaFinalizada DATE NOT NULL,
            cod_estado TEXT NOT NULL,
            observaciones TEXT,

            FOREIGN KEY (vehiculo_id) REFERENCES vehiculos(id),
            FOREIGN KEY (cod_estado) REFERENCES estados(codigo)
    );

    CREATE TABLE IF NOT EXISTS estados (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            codigo TEXT NOT NULL UNIQUE,
            descripcion TEXT NOT NULL
    );

    CREATE TABLE IF NOT EXISTS gastos (
          id INTEGER PRIMARY KEY AUTOINCREMENT,
          descripcion TEXT NOT NULL,
          importe REAL NOT NULL,
          fecha DATE,
          observaciones TEXT
    );

    CREATE TABLE IF NOT EXISTS ingresos (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        concepto TEXT NOT NULL,
        importe REAL NOT NULL,
        fecha DATE NOT NULL,
        cod_estado TEXT NOT NULL,
        observaciones TEXT,

        FOREIGN KEY (cod_estado) REFERENCES estados(codigo)
    );

    CREATE TABLE IF NOT EXISTS movimientos_asociaciones (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        tipo_movimiento TEXT NOT NULL,     -- "GASTO" o "INGRESO"
        movimiento_id INTEGER NOT NULL,    -- id del gasto o ingreso
        tipo_entidad TEXT NOT NULL CHECK(tipo_entidad IN ('CITAS')),  -- limitar los tipos de entidad por ahora
        -- Para añadir nuevas entidades permitidas en el futuro, simplemente incluirlas en la lista del CHECK:
        -- Ejemplo: CHECK(tipo_entidad IN ('CITAS','REPARACIONES','CLIENTES'))
        entidad_id INTEGER NOT NULL,       -- id del registro en la tabla relacionada
        observaciones TEXT
    );

    CREATE TABLE IF NOT EXISTS festivos (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        fecha DATE NOT NULL UNIQUE,
        descripcion TEXT,
        observaciones TEXT
    );

    CREATE INDEX IF NOT EXISTS idx_festivos_fecha ON festivos(fecha);


    CREATE TABLE IF NOT EXISTS vacaciones (
          id INTEGER PRIMARY KEY AUTOINCREMENT,
          mecanico_id INTEGER NOT NULL,       -- referencia a la tabla mecanicos
          fechaInicio DATE NOT NULL,
          fechaFin DATE NOT NULL,
          descripcion TEXT,
          observaciones TEXT,
          FOREIGN KEY (mecanico_id) REFERENCES mecanicos(id)
    );

    CREATE INDEX IF NOT EXISTS idx_vacaciones_fecha ON vacaciones(fechaInicio, fechaFin);



--INDICES
    CREATE INDEX IF NOT EXISTS idx_vehiculos_matricula ON vehiculos (matricula);

    CREATE INDEX IF NOT EXISTS idx_movimientos_asociaciones_movimiento
        ON movimientos_asociaciones (tipo_movimiento, movimiento_id);

    CREATE INDEX IF NOT EXISTS idx_movimientos_asociaciones_entidad
        ON movimientos_asociaciones (tipo_entidad, entidad_id);


--INSERT
    INSERT INTO estados (codigo,descripcion)VALUES ('ACTI', 'Estado del elemento activo');
    INSERT INTO estados (codigo,descripcion)VALUES ('BAJA', 'Estado del elemento de baja');
