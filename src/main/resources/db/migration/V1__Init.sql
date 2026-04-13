CREATE TABLE Users (
                       ID UUID PRIMARY KEY ,
                       Username varchar(30) UNIQUE NOT NULL ,
                       Wachtwoord varchar(255) NOT NULL ,
                       Rol varchar(30) NOT NULL ,
                       Email VARCHAR(255) UNIQUE NOT NULL,
                       CONSTRAINT uk_user_username UNIQUE (Username),
                       CONSTRAINT uk_user_email UNIQUE (Email)
);
CREATE TABLE Transacties (
    ID UUID PRIMARY KEY ,
    Aantal DECIMAL(15,2) NOT NULL,
    CreatieDatum TIMESTAMP WITH TIME ZONE NOT NULL,
    Beschrijving VARCHAR(255) NULL,
    User_id UUID NOT NULL ,

    CONSTRAINT fk_transactie_user
        FOREIGN KEY (User_id)
            REFERENCES Users(ID)
            ON DELETE CASCADE
);

CREATE TABLE Budget (
                        ID UUID PRIMARY KEY ,
                        Aantal DECIMAL(15,2) NOT NULL ,
                        BeginDatum DATE NULL ,
                        EindDatum Date Null ,
                        User_id UUID NOT NULL ,

                        CONSTRAINT fk_budget_user
                            FOREIGN KEY (User_id)
                                REFERENCES Users(ID)
                                ON DELETE CASCADE
);
CREATE TABLE Categorie (
                           ID UUID PRIMARY KEY ,
                           NAAM VARCHAR(30) ,
                           IsBelangrijk bit ,
                           User_id UUID NOT NULL ,

                            CONSTRAINT fk_categorie_user
                            FOREIGN KEY (User_id)
                            REFERENCES Users(ID)
                            ON DELETE CASCADE
);
CREATE TABLE BudgetIndeling (
                                ID UUID PRIMARY KEY,
                                Aantal DECIMAL(15,2),
                                Percentage REAL,
                                Budget_id UUID NOT NULL,
                                Categorie_id UUID NOT NULL,

                                CONSTRAINT fk_budgetIndeling_budget
                                    FOREIGN KEY (Budget_id)
                                        REFERENCES Budget(ID)
                                        ON DELETE CASCADE,

                                CONSTRAINT fk_budgetIndeling_categorie
                                    FOREIGN KEY (Categorie_id)
                                        REFERENCES Categorie(ID)
                                        ON DELETE CASCADE,

                                CONSTRAINT unique_budget_category UNIQUE (Budget_id, Categorie_id)
);