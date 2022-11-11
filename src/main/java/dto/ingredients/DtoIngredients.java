package dto.ingredients;

import java.util.List;

public class DtoIngredients {
    private boolean success;
    private List<DtoIngredient> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DtoIngredient> getData() {
        return data;
    }

    public void setData(List<DtoIngredient> data) {
        this.data = data;
    }

    public DtoIngredient getIngredientWithName(String name) {
        if (null != getData()) {
            for (DtoIngredient ingredient : getData()) {
                if (ingredient.getName().equals(name))
                    return ingredient;
            }
        }
        return null;
    }

}
