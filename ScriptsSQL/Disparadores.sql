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
EXECUTE PROCEDURE funcionFecha();

CREATE OR REPLACE FUNCTION funcionFechaComentario() RETURNS trigger AS
$$
BEGIN
	NEW.fecha:= current_date;
	RETURN NEW;
END;
$$ LANGUAGE 'plpgsql';

CREATE TRIGGER tb_fecha_comentario
BEFORE INSERT ON public.comentario
FOR EACH ROW
EXECUTE PROCEDURE funcionFechaComentario();