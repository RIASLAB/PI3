CREATE DATABASE  veterinaria_3;
USE veterinaria_3;
CREATE TABLE Cliente (
    cliente_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    telefono VARCHAR(20),
    email VARCHAR(100),
    direccion VARCHAR(200)
);

CREATE TABLE Mascota (
    mascota_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    especie VARCHAR(50),
    raza VARCHAR(50),
    fecha_nacimiento DATE,
    sexo ENUM('Macho', 'Hembra'),
    cliente_id INT,
    FOREIGN KEY (cliente_id) REFERENCES Cliente(cliente_id) ON DELETE CASCADE
);


CREATE TABLE Veterinario (
    veterinario_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    apellido VARCHAR(100),
    especialidad VARCHAR(100),
    telefono VARCHAR(20),
    email VARCHAR(100)
);

CREATE TABLE Catalogo (
    catalogo_id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion TEXT,
    precio DECIMAL(10, 2),
    stock INT NOT NULL DEFAULT 0,
    tipo_catalogo_id INT NOT NULL, 
    FOREIGN KEY (tipo_catalogo_id) REFERENCES Tipo_Catalogo(tipo_catalogo_id)
);
CREATE TABLE Tipo_Catalogo (
    tipo_catalogo_id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(255)
);

CREATE TABLE Cita (
    cita_id INT AUTO_INCREMENT PRIMARY KEY,
    fecha_hora DATETIME NOT NULL,
    motivo TEXT,
    mascota_id INT,
    veterinario_id INT,
    FOREIGN KEY (mascota_id) REFERENCES Mascota(mascota_id) ON DELETE CASCADE,
    FOREIGN KEY (veterinario_id) REFERENCES Veterinario(veterinario_id) ON DELETE SET NULL,
    CONSTRAINT unique_veterinario_fecha UNIQUE (fecha_hora, veterinario_id)
);

CREATE TABLE Ejecucion_Cita (
    ejecucion_cita_id INT AUTO_INCREMENT PRIMARY KEY,  
    cita_id INT NOT NULL,                             
    diagnostico TEXT,                                  
    fecha_ejecucion DATETIME NOT NULL,                 
    FOREIGN KEY (cita_id) REFERENCES Cita(cita_id) ON DELETE CASCADE,  
    CONSTRAINT unique_ejecucion_cita UNIQUE (cita_id)  
);


CREATE TABLE Receta (
    receta_id INT AUTO_INCREMENT PRIMARY KEY,
    ejecucion_cita_id INT NOT NULL,
    catalogo_id INT NOT NULL,
    observaciones TEXT,
    FOREIGN KEY (ejecucion_cita_id) REFERENCES Ejecucion_Cita(ejecucion_cita_id) ON DELETE CASCADE,
    FOREIGN KEY (catalogo_id) REFERENCES Catalogo(catalogo_id) ON DELETE CASCADE
);


CREATE TABLE Factura (
    factura_id INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME NOT NULL,
    total DECIMAL(10, 2),
    cliente_id INT,
    FOREIGN KEY (cliente_id) REFERENCES Cliente(cliente_id) ON DELETE SET NULL
);
CREATE TABLE Detalle_factura (
    detalle_id INT AUTO_INCREMENT PRIMARY KEY,
    factura_id INT,
    catalogo_id INT,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (factura_id) REFERENCES Factura(factura_id) ON DELETE CASCADE,
    FOREIGN KEY (catalogo_id) REFERENCES Catalogo(catalogo_id) ON DELETE SET NULL
);

INSERT INTO Tipo_Catalogo (descripcion) VALUES
('Producto'),
('Servicio');

INSERT INTO Catalogo (descripcion, precio, stock, tipo_catalogo_id) VALUES
('Consulta general veterinaria', 35.00, 0, 2),
('Aplicación de vacunas obligatorias', 60.00, 0, 2),
('Baño y corte de pelo (raza pequeña)', 28.00, 0, 2),
('Baño y corte de pelo (raza mediana)', 35.00, 0, 2),
('Limpieza dental con sedación', 85.00, 0, 2),
('Tratamiento antipulgas y desparasitación', 30.00, 0, 2),
('Corte de uñas y limpieza de oídos', 18.00, 0, 2),
('Microchip de identificación y registro', 42.00, 0, 2),
('Consulta de urgencias 24h', 90.00, 0, 2),
('Terapia postquirúrgica (por sesión)', 50.00, 0, 2),
('Sesión de fisioterapia animal', 45.00, 0, 2),
('Peluquería felina básica', 33.00, 0, 2);

INSERT INTO Catalogo (descripcion, precio, stock, tipo_catalogo_id) VALUES
('Shampoo para perros piel sensible 500ml', 19.99, 25, 1),
('Alimento para gatos adulto 5kg', 45.50, 40, 1),
('Juguete de goma para morder (perros)', 7.99, 100, 1),
('Rascador para gatos con base de sisal', 34.99, 15, 1),
('Transportadora mediana plástica', 65.00, 10, 1),
('Collar antipulgas grande (perros)', 14.99, 60, 1),
('Arena sanitaria para gatos 10kg', 12.75, 30, 1),
('Cepillo doble cara para pelo corto y largo', 11.50, 50, 1),
('Suplemento multivitamínico líquido 120ml', 22.00, 20, 1),
('Bolso mochila para transportar mascotas pequeñas', 48.90, 12, 1);

INSERT INTO Veterinario (nombre, apellido, especialidad, telefono, email) VALUES
('Ana', 'García Rojas', 'Medicina general', '3114567890', 'ana.garcia@lugovet.com'),
('Carlos', 'Martínez Pérez', 'Cirugía veterinaria', '3109876543', 'carlos.martinez@lugovet.com'),
('Laura', 'Hernández López', 'Dermatología animal', '3123456789', 'laura.hernandez@lugovet.com'),
('Jorge', 'Ramírez Soto', 'Odontología veterinaria', '3135678901', 'jorge.ramirez@lugovet.com'),
('María', 'Fernández Díaz', 'Rehabilitación y fisioterapia', '3141234567', 'maria.fernandez@lugovet.com'),
('David', 'Castro Mejía', 'Traumatología animal', '3156789012', 'david.castro@lugovet.com'),
('Paola', 'Ruiz Torres', 'Medicina felina', '3167890123', 'paola.ruiz@lugovet.com'),
('Esteban', 'Gómez Silva', 'Anestesiología veterinaria', '3178901234', 'esteban.gomez@lugovet.com'),
('Sofía', 'Moreno Ayala', 'Exóticos y silvestres', '3189012345', 'sofia.moreno@lugovet.com'),
('Julio', 'Navarro Quintero', 'Vacunación y prevención', '3190123456', 'julio.navarro@lugovet.com');