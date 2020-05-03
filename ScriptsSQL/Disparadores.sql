CREATE OR REPLACE FUNCTION funcionFecha() RETURNS trigger AS
$$
BEGIN
	NEW.fechainicio:= current_date;
	RETURN NEW;
END;
$$ LANGUAGE 'plpgsql';

CREATE TRIGGER tb_fecha_inicio
BEFORE INSERT ON public.iniciativa 
FOR EACH ROW
EXECUTE PROCEDURE funcionFecha()


CREATE OR REPLACE FUNCTION verificarEstado() RETURNS trigger AS
$$
BEGIN

	IF(OLD.estado_Propuesta = "En espera de revisión") THEN
		OLD.nombrePropuesta:= NEW.nombrePropuesta;
		OLD.descripcion:= NEW.descripcion;
		OLD.area:= NEW.area;
	END IF;
	RETURN NEW;
END;
$$ LANGUAGE 'plpgsql';

CREATE TRIGGER modificacionIniciativa
BEFORE UPDATE ON public.iniciativa 
FOR EACH ROW
EXECUTE PROCEDURE verificarEstado()