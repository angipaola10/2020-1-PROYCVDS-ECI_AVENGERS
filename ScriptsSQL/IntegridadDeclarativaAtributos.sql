ALTER TABLE Usuario ADD CONSTRAINT CK_Usuario_Id CHECK (id > 0 AND id <= 9999999999);
ALTER TABLE Usuario ADD CONSTRAINT CK_Usuario_Tid CHECK (tid IN ('CC', 'CE', 'TI'));
ALTER TABLE Usuario ADD CONSTRAINT CK_Usuario_Telefono CHECK (telefono > 0 AND telefono <= 9999999999);
ALTER TABLE Usuario ADD CONSTRAINT CK_Usuario_Clave CHECK (LENGTH(clave) > 7);
ALTER TABLE Rol ADD CONSTRAINT CK_Rol_Tipo CHECK (tipo IN ('Administrador', 'Proponente', 'PersonalPMO', 'Publico'));
ALTER TABLE Usario ADD CONSTRAINT CK_Usuario_Estado CHECK (tipo IN ('Activo', 'Inactivo'));
ALTER TABLE Iniciativa ADD CONSTRAINT CK_Iniciativa_Estado CHECK (tipo IN ('En espera de revisión', 'En revisión','Proyecto','Solucionado'));                                                          
