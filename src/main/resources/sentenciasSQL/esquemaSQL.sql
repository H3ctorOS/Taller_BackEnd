-- TABLAS
    CREATE TABLE IF NOT EXISTS vehiculos (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            matricula TEXT UNIQUE NOT NULL,
            modelo TEXT,
            marca TEXT,
            cod_estado TEXT NOT NULL,

            FOREIGN KEY (cod_estado) REFERENCES estados(codigo)
     );


    CREATE TABLE IF NOT EXISTS mecanicos (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            dni TEXT NOT NULL,
            nombre TEXT NOT NULL,
            cod_estado TEXT NOT NULL,

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

            FOREIGN KEY (cod_estado) REFERENCES estados(codigo)
    );


    CREATE TABLE IF NOT EXISTS reparaciones (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            concepto TEXT NOT NULL,
            vehiculo_id INTEGER NOT NULL,
            mecanico_id INTEGER,
            fechaInicio DATE,
            fechaFin DATE,

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

            FOREIGN KEY (vehiculo_id) REFERENCES vehiculos(id),
            FOREIGN KEY (cliente_id) REFERENCES clientes(id),
            FOREIGN KEY (cod_estado) REFERENCES estados(codigo)
    );

    CREATE TABLE IF NOT EXISTS CITAS (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            vehiculo_id INTEGER NOT NULL,
            cliente_id INTEGER NOT NULL,
            reparacion_id INTEGER,
            concepto TEXT NOT NULL,
            fecha DATE NOT NULL,
            cod_estado TEXT NOT NULL,

            FOREIGN KEY (vehiculo_id) REFERENCES vehiculos(id),
            FOREIGN KEY (cliente_id) REFERENCES clientes(id),
            FOREIGN KEY (reparacion_id) REFERENCES reparaciones(id),
            FOREIGN KEY (cod_estado) REFERENCES estados(codigo)
    );

    CREATE TABLE IF NOT EXISTS ESTADOS (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            codigo TEXT NOT NULL,
            descripcion TEXT NOT NULL
    );

--INDICES
    CREATE INDEX IF NOT EXISTS idx_vehiculos_matricula ON vehiculos (matricula);

--INSERT
    INSERT INTO estados (codigo,descripcion)VALUES ('ACTI', 'Estado del elemento activo');
    INSERT INTO estados (codigo,descripcion)VALUES ('BAJA', 'Estado del elemento de baja');
