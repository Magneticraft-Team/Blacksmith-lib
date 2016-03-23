package net.darkaqua.blacksmith.lang;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LangManager implements ILangManager {

    private Map<String, Map<String, String>> languages = new HashMap<>();
    private File folder;

    LangManager(File folder) {
        this.folder = folder;
    }

    @Override
    public void addName(Object obj, String name, String lang) {
        if (obj == null) { return; }
        if (name == null) { return; }
        if (lang == null) { return; }
        Map<String, String> map = languages.get(lang);
        if (map == null) {
            map = new HashMap<>();
            languages.put(lang, map);
        }
        if (obj instanceof ItemStack) {
            map.put(((ItemStack) obj).getUnlocalizedName() + ".name", name);
        } else if (obj instanceof Block) {
            map.put(((Block) obj).getUnlocalizedName() + ".name", name);
        } else if (obj instanceof Item) {
            map.put(((Item) obj).getUnlocalizedName() + ".name", name);
        } else if (obj instanceof FluidStack) {
            map.put(((FluidStack) obj).getUnlocalizedName(), name);
        } else if (obj instanceof String) {
            map.put(obj + ".name", name);
        }
    }

    @Override
    public void save() {
        languages.keySet().forEach(this::save);
    }

    @Override
    public void save(String lang) {
        Map<String, String> map = languages.get(lang);
        File file = new File(folder, lang + ".lang");
        try (Writer w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)))) {
            for (Map.Entry<String, String> s : map.entrySet()) {
                w.write(s.getKey() + "=" + s.getValue() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load() {
        if (folder != null) {
            for (File file : folder.listFiles()) {
                if (file.getName().contains(".lang")) {
                    load(file.getName().replace(".lang", ""));
                }
            }
        }
    }

    @Override
    public void load(String lang) {
        try {
            File file = new File(folder, lang+".lang");
            Map<String, String> map = new HashMap<>();
            Scanner scanner = new Scanner(new FileReader(file));
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                int index = line.indexOf('=');
                if (index != -1) {
                    map.put(line.substring(0, index), line.substring(index, line.length()));
                }
            }
            languages.put(lang, map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public File getFolder() {
        return folder;
    }

    @Override
    public void clear(){
        languages.clear();
    }
}
