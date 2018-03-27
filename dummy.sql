SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `jfract4d`
--

-- --------------------------------------------------------

--
-- Table structure for table `infraction`
--

CREATE Database jfract4d;
use jfract4d;

CREATE TABLE `infraction` (
  `id` char(8) NOT NULL,
  `user` char(18) NOT NULL,
  `giver` char(18) NOT NULL,
  `infraction_type` int(11) NOT NULL,
  `time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `effective` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `infraction`
--

INSERT INTO `infraction` (`id`, `user`, `giver`, `infraction_type`, `time`, `effective`) VALUES
('4735a01f', '156583677814374400', '268170701486161920', 8, '2018-03-27 18:19:49', 1),
('7a2eb1f6', '156583677814374400', '123955881116172288', 7, '2018-03-27 18:20:44', 1),
('ef5d64ae', '156583677814374400', '123955881116172288', 6, '2018-03-27 18:16:29', 1);

-- --------------------------------------------------------

--
-- Table structure for table `infraction_category`
--

CREATE TABLE `infraction_category` (
  `id` int(11) NOT NULL,
  `name` varchar(40) DEFAULT NULL,
  `points` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `infraction_category`
--

INSERT INTO `infraction_category` (`id`, `name`, `points`) VALUES
(4, 'minor', 1),
(5, 'major', 3),
(6, 'warning', 0);

-- --------------------------------------------------------

--
-- Table structure for table `infraction_type`
--

CREATE TABLE `infraction_type` (
  `id` int(11) NOT NULL,
  `name` varchar(40) NOT NULL,
  `description` text,
  `category` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `infraction_type`
--

INSERT INTO `infraction_type` (`id`, `name`, `description`, `category`) VALUES
(6, 'spam', 'given to a user that spams', 4),
(7, 'illegal content', 'i', 5),
(8, 'smuggg', 'smug boi', 6);

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` char(18) NOT NULL,
  `name` varchar(50) NOT NULL,
  `level` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `name`, `level`) VALUES
('000000000000000000', 'User', 0),
('111111111111111111', 'Admin', 3);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `discord_id` char(18) NOT NULL,
  `role` char(18) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`discord_id`, `role`) VALUES
('156583677814374400', '000000000000000000'),
('159780137645244417', '000000000000000000'),
('123955881116172288', '111111111111111111'),
('268170701486161920', '111111111111111111');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `infraction`
--
ALTER TABLE `infraction`
  ADD PRIMARY KEY (`id`),
  ADD KEY `giver_fk` (`giver`),
  ADD KEY `target_fk` (`user`),
  ADD KEY `type_fk` (`infraction_type`);

--
-- Indexes for table `infraction_category`
--
ALTER TABLE `infraction_category`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `infraction_type`
--
ALTER TABLE `infraction_type`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`),
  ADD KEY `category_fk` (`category`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`discord_id`),
  ADD KEY `role_fl` (`role`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `infraction_category`
--
ALTER TABLE `infraction_category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `infraction_type`
--
ALTER TABLE `infraction_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `infraction`
--
ALTER TABLE `infraction`
  ADD CONSTRAINT `giver_fk` FOREIGN KEY (`giver`) REFERENCES `user` (`discord_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `target_fk` FOREIGN KEY (`user`) REFERENCES `user` (`discord_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `type_fk` FOREIGN KEY (`infraction_type`) REFERENCES `infraction_type` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `infraction_type`
--
ALTER TABLE `infraction_type`
  ADD CONSTRAINT `category_fk` FOREIGN KEY (`category`) REFERENCES `infraction_category` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `role_fl` FOREIGN KEY (`role`) REFERENCES `role` (`id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
