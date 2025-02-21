-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2025. Feb 21. 14:08
-- Kiszolgáló verziója: 10.4.27-MariaDB
-- PHP verzió: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `vizsgaremek_db`
--

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `order`
--

CREATE TABLE `order` (
  `id` int(11) NOT NULL,
  `cart` varchar(191) NOT NULL,
  `shipping_address` varchar(191) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `other`
--

CREATE TABLE `other` (
  `id` int(11) NOT NULL,
  `description` varchar(191) NOT NULL,
  `img` varchar(191) NOT NULL,
  `productId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `name` varchar(191) NOT NULL,
  `price` int(11) NOT NULL,
  `category` enum('Tea','Other') NOT NULL,
  `tea_id` int(11) DEFAULT NULL,
  `other_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `tea`
--

CREATE TABLE `tea` (
  `id` int(11) NOT NULL,
  `type` varchar(191) NOT NULL,
  `flavor` varchar(191) NOT NULL,
  `productId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `token`
--

CREATE TABLE `token` (
  `token` varchar(191) NOT NULL,
  `userId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- A tábla adatainak kiíratása `token`
--

INSERT INTO `token` (`token`, `userId`) VALUES
('eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjEsInVzZXJuYW1lIjoic3RyaW5nIiwiaWF0IjoxNzM5OTcwOTUwfQ.hEpHraUIqdWbgvuIth8l9KEf2riMGxrk59qmJs_E77s', 1),
('eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjEsInVzZXJuYW1lIjoic3RyaW5nIiwiaWF0IjoxNzM5OTY0MDcyfQ.gRDliLTnj0a2ehrsK9RDeDkiLLFYTxh1Pkz5V3z5gWc', 1),
('eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjEsInVzZXJuYW1lIjoic3RyaW5nIiwiaWF0IjoxNzM5OTY3NjE0fQ.7cSX8gIKySyxL1TkAAXvlBCLN9yC5vDgDOWV63gFb-U', 1),
('eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjIsInVzZXJuYW1lIjoiYWRtaW4iLCJpYXQiOjE3Mzk5NjA2NTV9.DxncL7ACE-0bkBnlrYKLEiYtQvJncV_fcwQo5Ip40LU', 2),
('eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjIsInVzZXJuYW1lIjoiYWRtaW4iLCJpYXQiOjE3Mzk5Njg0OTJ9.Bvjy40ZhgDmRl6UNbSmfLzUlgtwJQCmETkq6wfSvrFU', 2),
('eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjIsInVzZXJuYW1lIjoiYWRtaW4iLCJpYXQiOjE3Mzk5NjgyMzB9.kodb8NkNwdUSHx6NrWuGNRnd9DU2xmacoap_cr3KG7M', 2),
('eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjIsInVzZXJuYW1lIjoiYWRtaW4iLCJpYXQiOjE3Mzk5NzA3NDR9.XscsAKUULSPN0p7xAuYr2i_CDnJIxmKiM7YX-fc_7Vw', 2);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(191) NOT NULL,
  `email` varchar(191) NOT NULL,
  `password` varchar(191) NOT NULL,
  `shipping_address` varchar(191) DEFAULT NULL,
  `role` enum('Admin','User') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- A tábla adatainak kiíratása `user`
--

INSERT INTO `user` (`id`, `username`, `email`, `password`, `shipping_address`, `role`) VALUES
(1, 'string', 'string', '$2b$10$z5V3lpDNRB3AEI6Ee81/fO74rT8EZUbckBaG8.mfiZMio2HV2yrk2', 'string', 'User'),
(2, 'admin', 'string', '$2b$10$YLx6xkQfN0Kj1PsYTD2b8eFVRk1H2P0o6iWoSwt/yWDDtzWBd6qbO', 'string', 'Admin');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `_ordertoproduct`
--

CREATE TABLE `_ordertoproduct` (
  `A` int(11) NOT NULL,
  `B` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `_ordertouser`
--

CREATE TABLE `_ordertouser` (
  `A` int(11) NOT NULL,
  `B` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `order`
--
ALTER TABLE `order`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `other`
--
ALTER TABLE `other`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Other_productId_key` (`productId`);

--
-- A tábla indexei `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Product_tea_id_key` (`tea_id`),
  ADD UNIQUE KEY `Product_other_id_key` (`other_id`);

--
-- A tábla indexei `tea`
--
ALTER TABLE `tea`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Tea_productId_key` (`productId`);

--
-- A tábla indexei `token`
--
ALTER TABLE `token`
  ADD PRIMARY KEY (`token`),
  ADD KEY `Token_userId_fkey` (`userId`);

--
-- A tábla indexei `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `User_username_key` (`username`);

--
-- A tábla indexei `_ordertoproduct`
--
ALTER TABLE `_ordertoproduct`
  ADD UNIQUE KEY `_OrderToProduct_AB_unique` (`A`,`B`),
  ADD KEY `_OrderToProduct_B_index` (`B`);

--
-- A tábla indexei `_ordertouser`
--
ALTER TABLE `_ordertouser`
  ADD UNIQUE KEY `_OrderToUser_AB_unique` (`A`,`B`),
  ADD KEY `_OrderToUser_B_index` (`B`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `other`
--
ALTER TABLE `other`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `tea`
--
ALTER TABLE `tea`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Megkötések a kiírt táblákhoz
--

--
-- Megkötések a táblához `other`
--
ALTER TABLE `other`
  ADD CONSTRAINT `Other_productId_fkey` FOREIGN KEY (`productId`) REFERENCES `product` (`id`) ON UPDATE CASCADE;

--
-- Megkötések a táblához `tea`
--
ALTER TABLE `tea`
  ADD CONSTRAINT `Tea_productId_fkey` FOREIGN KEY (`productId`) REFERENCES `product` (`id`) ON UPDATE CASCADE;

--
-- Megkötések a táblához `token`
--
ALTER TABLE `token`
  ADD CONSTRAINT `Token_userId_fkey` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Megkötések a táblához `_ordertoproduct`
--
ALTER TABLE `_ordertoproduct`
  ADD CONSTRAINT `_OrderToProduct_A_fkey` FOREIGN KEY (`A`) REFERENCES `order` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `_OrderToProduct_B_fkey` FOREIGN KEY (`B`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Megkötések a táblához `_ordertouser`
--
ALTER TABLE `_ordertouser`
  ADD CONSTRAINT `_OrderToUser_A_fkey` FOREIGN KEY (`A`) REFERENCES `order` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `_OrderToUser_B_fkey` FOREIGN KEY (`B`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
