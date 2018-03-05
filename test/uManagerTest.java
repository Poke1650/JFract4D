/**
 *
 * @author Antoine Gagnon
 */
public class uManagerTest {
    /*
            JFract.getDatabaseManager().getConnection().createStatement().execute("DELETE FROM user WHERE 1 = 1");
            JFract.getDatabaseManager().getConnection().createStatement().execute("DELETE FROM role WHERE 1 = 1");
            
            Role adminRole = new DiscordRole("375764853895462912", "Admin", 3);
            Role moderatorRole = new DiscordRole("375764735070830594", "Moderator", 2);
            
            uManager.addRole(adminRole);
            uManager.addRole(moderatorRole);
            
            System.out.println("Roles after adding Moderator and Admin");
            for(Role r : uManager.getRoles()) {
                System.out.println(r.getName());
            }
            
            System.out.println("Role for id: " + adminRole.getID() + " = " + uManager.getRole(adminRole.getID()).getName());
            
            System.out.println("---------------------------------------------------------------\n");
            
            uManager.removeRole(moderatorRole.getID());
            
            System.out.println("Roles after removing moderator");
            
            uManager.getRoles().forEach((r) -> {
                System.out.println(r.getName());
            });
            
            System.out.println("---------------------------------------------------------------\n");
            
            User usr1 = new DiscordUser("141990014346199040");
            User usr2 = new DiscordUser("227170835323027456", adminRole);
            
            uManager.addUsers(Arrays.asList(usr1, usr2));
            
            System.out.println("Users after adding 2");
            
            uManager.getUsers().forEach((r) -> {
                System.out.println(r.getID() + " role: " + (r.getRole() == null ? "null" : r.getRole().getName()));
                Role rl = uManager.getRoleForUser(r); 
                System.out.println("Role: " + (rl == null ? "null" : rl.getName()));
            });
            
            System.out.println("---------------------------------------------------------------\n");
            
            System.out.println("Users after removing 227170835323027456");
            
            uManager.removeUser(usr1.getID());
            
            uManager.getUsers().forEach((r) -> {
                System.out.println(r.getID() + " role: " + (r.getRole() == null ? "null" : r.getRole().getName()));
            });

            System.out.println("---------------------------------------------------------------\n");
            
            System.out.println("Admin role -> Administrator");
            uManager.updateRole(adminRole.getID(), new DiscordRole(adminRole.getID(), "Administrator", adminRole.getLevel()));
            uManager.getRoles().forEach((r) -> {
                System.out.println(r.getName());
            });
            
            System.out.println("---------------------------------------------------------------\n");
            
            System.out.println("Usr1 role null -> Administrator");
            uManager.addUser(usr1);
            uManager.updateUserRole(usr1.getID(), adminRole);
            uManager.getUsers().forEach((r) -> {
                System.out.println(r.getID() + " role: " + (r.getRole() == null ? "null" : r.getRole().getName()));
            });
           */
}
