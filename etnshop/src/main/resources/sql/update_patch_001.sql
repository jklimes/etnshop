ALTER TABLE `product`
  ADD COLUMN `SERIAL_NUMBER` CHAR(11) NOT NULL DEFAULT '00000000000'
  AFTER `name`;

DROP PROCEDURE IF EXISTS fill_serial_numbers;

DELIMITER $$


CREATE PROCEDURE fill_serial_numbers()
  BEGIN

    DECLARE v_finished INTEGER DEFAULT 0;
    DECLARE v_id INT(11);

    -- declare cursor for employee email
    DECLARE product_cursor CURSOR FOR
      SELECT id
      FROM product;

    -- declare NOT FOUND handler
    DECLARE CONTINUE HANDLER
    FOR NOT FOUND SET v_finished = 1;

    OPEN product_cursor;

    get_product: LOOP

      FETCH product_cursor
      INTO v_id;

      IF v_finished = 1
      THEN
        LEAVE get_product;
      END IF;

      -- build email list
      UPDATE product p
      SET p.serial_number = lpad(concat(v_id, ''), 11, '0')
      WHERE p.id = v_id;

    END LOOP get_product;

    CLOSE product_cursor;

  END$$

DELIMITER ;

CALL fill_serial_numbers();

DROP PROCEDURE IF EXISTS fill_serial_numbers;