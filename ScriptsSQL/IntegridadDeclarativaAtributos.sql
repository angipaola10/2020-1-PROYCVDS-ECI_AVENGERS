ALTER TABLE Usuario ADD CONSTRAINT CK_Usuario_Id CHECK (id > 0 AND id <= 9999999999);
ALTER TABLE Usuario ADD CONSTRAINT CK_Usuario_Tid CHECK (tid IN ('CC', 'CE', 'TI'));
ALTER TABLE Usuario ADD CONSTRAINT CK_Usuario_Telefono CHECK (telefono > 0 AND telefono <= 9999999999);
ALTER TABLE Usuario ADD CONSTRAINT CK_Usuario_Correo CHECK (correo LIKE ('%@hotmail.com') OR correo LIKE ('%yahoo.com') OR correo LIKE ('%gmail.com') OR correo LIKE ('%@mail.escuelaing.edu.co'));
--ALTER TABLE Usuario ADD CONSTRAINT CK_Usuario_Clave CHECK (LENGTH(clave) > 7);
ALTER TABLE Rol ADD CONSTRAINT CK_Rol_Tipo CHECK (tipo IN ('Administrador', 'Proponente', 'Personal PMO', 'Publico');
