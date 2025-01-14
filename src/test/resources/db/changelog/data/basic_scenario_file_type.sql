TRUNCATE document_type CASCADE;
TRUNCATE file_type CASCADE;

/***********************************************************************************************************************
 *  Document Types
 **********************************************************************************************************************/
INSERT INTO "document_type" ("id", "code", "name", "visible", "creationTimestamp", "lastUpdateTimestamp")
VALUES (1, 'GENX', 'Name GENX', true, '2024-05-08 20:36:44', '2024-05-08 20:36:50');

INSERT INTO "document_type" ("id", "code", "name", "visible", "creationTimestamp", "lastUpdateTimestamp")
VALUES (2, 'GENY', 'Name GENY', true, '2024-05-08 20:36:44', '2024-05-08 20:36:50');

INSERT INTO "document_type" ("id", "code", "name", "visible", "creationTimestamp", "lastUpdateTimestamp")
VALUES (3, 'GENZ', 'Name GENZ', false, '2024-05-08 20:36:44', '2024-05-08 20:36:50');

/***********************************************************************************************************************
 *  File Types
 **********************************************************************************************************************/
INSERT INTO "file_type" ("id", "name", "documentTypeCode", "visible", "creationTimestamp", "lastUpdateTimestamp")
VALUES (1, 'ABC', 'GENX', true, '2024-05-08 20:36:44', '2024-05-08 20:36:50');

INSERT INTO "file_type" ("id", "name", "documentTypeCode", "visible", "creationTimestamp", "lastUpdateTimestamp")
VALUES (2, 'BCD', 'GENX', true, '2024-05-08 20:36:44', '2024-05-08 20:36:50');

INSERT INTO "file_type" ("id", "name", "documentTypeCode", "visible", "creationTimestamp", "lastUpdateTimestamp")
VALUES (3, 'CDE', 'GENY', true, '2024-05-08 20:36:44', '2024-05-08 20:36:50');

INSERT INTO "file_type" ("id", "name", "documentTypeCode", "visible", "creationTimestamp", "lastUpdateTimestamp")
VALUES (4, 'DEF', 'GENZ', false, '2024-05-08 20:36:44', '2024-05-08 20:36:50');
