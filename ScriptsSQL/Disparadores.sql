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
