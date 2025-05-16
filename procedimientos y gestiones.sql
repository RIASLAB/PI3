use veterinaria_3
DELIMITER $$

CREATE PROCEDURE insertar_cliente (
    IN p_nombre VARCHAR(50),
    IN p_apellido VARCHAR(50),
    IN p_telefono VARCHAR(20),
    IN p_email VARCHAR(100),
    IN p_direccion VARCHAR(100)
)
BEGIN
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    BEGIN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al insertar cliente';
    END;

    INSERT INTO Cliente (nombre, apellido, telefono, email, direccion)
    VALUES (p_nombre, p_apellido, p_telefono, p_email, p_direccion);
END$$


DELIMITER $$
CREATE PROCEDURE eliminar_cliente (
    IN p_cliente_id INT
)
BEGIN
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    BEGIN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al eliminar cliente';
    END;

    DELETE FROM Cliente WHERE cliente_id = p_cliente_id;
END$$


DELIMITER $$
CREATE PROCEDURE insertar_mascota (
    IN p_nombre VARCHAR(50),
    IN p_especie VARCHAR(50),
    IN p_raza VARCHAR(50),
    IN p_fecha_nac DATE,
    IN p_sexo VARCHAR(10),
    IN p_cliente_id INT
)
BEGIN
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    BEGIN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al insertar mascota';
    END;

    INSERT INTO Mascota (nombre, especie, raza, fecha_nacimiento, sexo, cliente_id)
    VALUES (p_nombre, p_especie, p_raza, p_fecha_nac, p_sexo, p_cliente_id);
END$$


DELIMITER $$
CREATE PROCEDURE eliminar_mascota (
    IN p_mascota_id INT
)
BEGIN
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    BEGIN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al eliminar mascota';
    END;

    DELETE FROM Mascota WHERE mascota_id = p_mascota_id;
END$$


DELIMITER $$
CREATE PROCEDURE insertar_cita (
    IN p_fecha_hora TIMESTAMP,
    IN p_motivo VARCHAR(100),
    IN p_mascota_id INT,
    IN p_veterinario_id INT
)
BEGIN
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    BEGIN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al insertar cita';
    END;

    INSERT INTO Cita (fecha_hora, motivo, mascota_id, veterinario_id)
    VALUES (p_fecha_hora, p_motivo, p_mascota_id, p_veterinario_id);
END$$


DELIMITER $$
CREATE PROCEDURE insertar_ejecucion_cita (
    IN p_cita_id INT,
    IN p_diagnostico TEXT,
    IN p_fecha_ejecucion TIMESTAMP,
    OUT p_ejecucion_id INT
)
BEGIN
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    BEGIN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al insertar ejecución de cita';
    END;

    INSERT INTO Ejecucion_cita (cita_id, diagnostico, fecha_ejecucion)
    VALUES (p_cita_id, p_diagnostico, p_fecha_ejecucion);

    SET p_ejecucion_id = LAST_INSERT_ID();
END$$



DELIMITER $$
CREATE PROCEDURE insertar_receta (
    IN p_catalogo_id INT,
    IN p_ejecucion_cita_id INT,
    IN p_observaciones TEXT
)
BEGIN
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
    BEGIN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al insertar receta';
    END;

    INSERT INTO Receta (catalogo_id, ejecucion_cita_id, observaciones)
    VALUES (p_catalogo_id, p_ejecucion_cita_id, p_observaciones);
END$$
DELIMITER //
CREATE PROCEDURE consultar_clientes()
BEGIN
    SELECT * FROM Cliente;
END //

DELIMITER //
CREATE PROCEDURE consultar_mascotas()
BEGIN
    SELECT * FROM Mascota;
END //
DELIMITER //
CREATE PROCEDURE consultar_citas()
BEGIN
    SELECT * FROM Cita;
END //
DELIMITER ;
